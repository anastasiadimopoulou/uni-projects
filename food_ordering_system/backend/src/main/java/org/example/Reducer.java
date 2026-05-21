package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import android.*;
public class Reducer {
    int port = 8080;

    void openReducer() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Reducer started. Listening on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New worker connected.");
                ActionR reducerAction = new ActionR(clientSocket);
                new Thread(reducerAction).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Reducer().openReducer();
    }

    private static class ActionR extends Thread {
        private final Socket clientSocket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        private  ObjectOutputStream masterout;
        private  ObjectInputStream masterin;
        Socket socketmaster= null;

        private static final Map<Integer, Set<Integer>> clientWorkerMap = Collections.synchronizedMap(new HashMap<>());

        private static final Map<Integer, List<Shop>> clientShops = Collections.synchronizedMap(new HashMap<>());

        ActionR(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {

            try {

                out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.flush();
                in = new ObjectInputStream(clientSocket.getInputStream());




                socketmaster = new Socket("localhost", 4321);
                masterout= new ObjectOutputStream(socketmaster.getOutputStream());
                masterout.flush();
                masterin = new ObjectInputStream(socketmaster.getInputStream());

                Request rmc = new Request("Connection",null,null,null,null,null,0,0,"Reducer",0,null,null,null,null);
                masterout.writeObject(rmc);     //reducer tries to connect to master
                masterout.flush();



                while (true) {
                    Request r = (Request) in.readObject();

                    if ("Connection".equals(r.getType())) { // Worker ---> Reducer , worker is connected to reducer
                        System.out.println("Reducer connected to Worker with ID: " + r.getID());

                    } else if ("FilterShopsworkertoreducer".equals(r.getType())) { // Worker ---> Reducer
                                                                                    // reducer takes the shops from the worker (map) and makes (reduce)
                        int clientId = r.getFilter().getClientid();
                        int workerId = r.getID();
                        int total_number_workers = r.getProductamount();
                        System.out.println("total_amount_workers in REDUCER :" + total_number_workers);
                        List<Shop> filteredShops = r.getShopList();


                        //format : < clientid , Set με workers<1,2,3> >
                        //When the client ID does not exist, I create the Set that will contain the worker IDs; otherwise, I just add the worker ID
                        synchronized (clientWorkerMap) {
                            clientWorkerMap.computeIfAbsent(clientId, k -> new HashSet<>()).add(workerId);
                        }


                        // Format: <clientId, ArrayList<Shop>>
                        // If we haven't encountered the clientId before, we initialize the ArrayList with the products.
                        // If a request has come from another worker, we then add that worker's shops to the ArrayList.
                        // This way, in the end, the ArrayList will contain all the shops sent by the workers.

                        synchronized (clientShops) {
                            clientShops.computeIfAbsent(clientId, k -> Collections.synchronizedList(new ArrayList<>())).addAll(filteredShops);
                        }

                        // If the size of the Set containing the workers is equal to the total number of workers, then we're done with this client.
                        synchronized (clientWorkerMap) {
                        if (clientWorkerMap.get(clientId).size() == total_number_workers) {
                            System.out.println("ALL WORKERS have sent filtered shops" + clientId);


                            List<Shop> originalShops = clientShops.get(clientId);

                            ArrayList<Shop> finalShops;

                            synchronized (originalShops) {
                                finalShops = new ArrayList<>(originalShops);
                            }


                            // reducer -> master (finalshops)
                            if (masterout != null) {
                                Request r_master = new Request("finalShops", null, null, null, finalShops, null, 0, 0, "Reducer", clientId, null, null, null, null);
                                masterout.writeObject(r_master);    //Reducer ---> ClientHandler
                                masterout.flush();                  //takes the final shops when all workers have send the answers from filters and send them to master

                                System.out.println("Sent final shop list to Master for client: " + clientId);
                            }



                            // Clean up for the next requests from the same client
                            clientWorkerMap.remove(clientId);
                            clientShops.remove(clientId);

                            for (Shop shop : finalShops) {
                                System.out.println(shop.getShopName());
                            }
                        }
                    }


                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error in Reducer handler: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException ignored) {}
            }
        }
    }

}

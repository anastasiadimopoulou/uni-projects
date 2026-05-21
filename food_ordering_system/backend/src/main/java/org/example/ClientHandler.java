package org.example;
import android.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        System.out.println("ClientHandler started.");
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());

           // while (!clientSocket.isClosed()) {
            while(true) {
                try {
                    Object object = in.readObject();

                    if (object instanceof Request request) {
                        System.out.println("Received request from client: " + request.getClientType());
                        processRequest(in, out, request);
                    }



                } catch (EOFException e) {
                    System.out.println("Client disconnected.");
                    break;
                } catch (ClassNotFoundException | IOException e) {
                    System.out.println("Exception while reading object: " + e.getMessage());
                    break;
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted.");
                    Thread.currentThread().interrupt();
                    break;
                }
            //}
            }

        } catch (IOException e) {
            System.out.println("IOException in ClientHandler: " + e.getMessage());
        } finally {

        }
    }

    private void processRequest(ObjectInputStream in, ObjectOutputStream out, Request request) throws IOException, InterruptedException, ClassNotFoundException {
        String clientType = request.getClientType();
        System.out.println("Processing request from: " + clientType);

        switch (clientType) {
            case "Manager":
                Master.managerOutputs.put(request.getID(), out);
                handleManager(in, out, request);
                break;

            case "Worker":
                handleWorker(in, out,request);
                break;
            case "Client":
                handleClient(in, out, request);
                break;
            case "Reducer":
                handleReducer(in, out, request);
                break;
            default:
                System.out.println("Unknown client type.");
        }
    }

    private void handleReducer(ObjectInputStream in, ObjectOutputStream out, Request r) throws IOException {
        System.out.println("Handling Reducer request...");
        if ("finalShops".equals(r.getType())) {                 //Reducer ---> ClientHandler
            ArrayList<Shop> filtered_shops = r.getShopList();   //takes all the shops from map-reducing and send them to client
            int clientid = r.getID();
            ObjectOutputStream out3 = Master.clientOutputs.get(clientid);
            if (out3 != null) {
                out3.writeObject("filtershops");//ClientHandler ---> Client
                out3.flush();                   //has been successfully the map-reduce and naw he have the correct shops and we want to send them to client in order to make an order
                out3.writeObject(filtered_shops);
                out3.flush();
            } else {
                System.out.println("No output stream found for client ID: " + clientid);
            }
        }
    }

    private void handleManager(ObjectInputStream in, ObjectOutputStream out, Request request) throws InterruptedException, IOException {
        System.out.println("Handling Manager request..."+request.getType());
        ActionForManager action = new ActionForManager(in, out, request);
        action.start();
        action.join();

        Master.connmanagers.put(0, out);

        ArrayList<Shop> shopsFromAction = action.getShops();


        for (Shop shop : shopsFromAction) {
            if (shop.getShopName().equals(request.getShopName())) {


                Request r = action.getRequest();
                switch (r.getType()) {
                    case "IncreaseAmount", "DecreaseAmount" -> {
                        Shop s= Master.updateproductList(shop, r.getProduct().getProductName(), r.getProductamount());//Manager ---> ClientHandler
                                                                                                                    //clienthandler takes the shop and send it to manager to update his menu
                        out.writeObject("UpdatedShop");
                        out.writeObject(s);
                        out.flush();

                        for (int clientId : Master.clientOutputs.keySet()) {
                            ObjectOutputStream clientOut = Master.clientOutputs.get(clientId);
                            if (clientOut != null) {
                                clientOut.writeObject("UpdatedShop");
                                clientOut.writeObject(s);
                                clientOut.flush();
                            }
                        }
                    }
                    case "NewProductmanager" -> {                                                                  //Manager ---> ClientHandler
                        System.out.println("ΝΕΟ ΠΡΟΙΟΝ ΕΦΤASΕ CLIENTHANDLER");
                        Shop s=Master.addnewproduct(shop, r.getProduct());                                  //clienthandler receive the new product and shop and send it to clients via Master



                                out.writeObject("UpdatedShop");
                                out.writeObject(s);
                                out.flush();
                                System.out.println("CLIENTHANDLER ESTEILE TO UPDATEDSHOP ston manager");



                    }
                    case "UnavailableProduct" -> Master.unavailableproduct(shop, r.getProduct());       //Manager ---> ClientHandler
                                                                                                        //clienthandler receive the new product and shop and send it to clients via Master with state = false
                }
            }
        }
    }


    private void handleWorker(ObjectInputStream in, ObjectOutputStream out, Request request) throws InterruptedException {
        System.out.println("Handling Worker request..."+request.getType());


        if ("Connection".equals(request.getType())) {// a worker has been connected to server and we add it to hashmap connworkers (connected workers)
            synchronized (Master.connworkers) {
                if (Master.connworkers.containsKey(request.getHash_worker())) {
                    try {
                        Master.connworkers.get(request.getHash_worker()).close(); // old stream
                    } catch (IOException e) {
                        System.out.println("Couldn't close old stream for worker " + request.getHash_worker());
                    }
                }
                Master.connworkers.put(request.getHash_worker(), out);}
        }


        ActionForWorker action = new ActionForWorker(in, out, request);
        action.start();
        action.join();

        System.out.println("Worker request processed.");
    }




    public void handleClient(ObjectInputStream in, ObjectOutputStream out, Request request) throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("Handling Client request...");
        System.out.println("Starting action for Client ");
        ActionForClient action3 = new ActionForClient(in, out, request, null);
        Thread t2 = new Thread(action3);
        t2.start();
        t2.join();

        int clientId = request.getID();

        if (!clientSocket.isClosed()) {
            Master.addClientFilter(clientId, action3.request.getFilter());
            System.out.println("Received filter from client");
            sendFiltersToWorkers(action3.request.getFilter());

            if (Master.clientOutputs.containsKey(clientId)) {
                try {
                    Master.clientOutputs.get(clientId).close(); //kill an old stream with the same id
                } catch (IOException e) {
                    System.out.println("Couldn't close previous stream for client " + clientId);
                }
            }

            Master.addClientOutput(clientId, out); //add new client


            Master.printAllClientFilters();
        } else {
            System.out.println("Client socket already closed, skipping filter/outputstream store.");
        }


    }



    public static void sendFiltersToWorkers(Filter filter) {
        for (var entry : Master.connworkers.entrySet()) {
            int workerId = entry.getKey();
            ObjectOutputStream workerOut = entry.getValue();
            try {
                Request nf = new Request("NewFilters", null, null, null, null, null, 0, Master.workers.size(), "Master", workerId, filter, null, null, null);
                workerOut.writeObject(nf);      //ClientHandler ---> Worker
                workerOut.flush();              //takes the filters from the user(foodcategory,stars,$) and send them to worker to do the mapping

            } catch (IOException e) {
                System.out.println("Error sending filter to Worker " + workerId);
                e.printStackTrace();
            }
        }
    }
}

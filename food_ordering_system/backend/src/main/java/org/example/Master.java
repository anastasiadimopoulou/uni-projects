package org.example;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import android.Filter;
import android.*;
import static org.example.ActionForManager.shops;
import android.*;
public class  Master {


    public static HashMap<Integer, Filter> clientFilters = new HashMap<>();
    public static HashMap<Integer, ObjectOutputStream> clientOutputs = new HashMap<>();
    public static HashMap<Integer, ObjectOutputStream> managerOutputs = new HashMap<>();
    public static HashMap<Integer, ObjectOutputStream> connmanagers = new HashMap<>();

    static List<Worker> workers;
    public static HashMap<Integer,ObjectOutputStream> connworkers = new HashMap<>();
    int port=4321;

    public static ArrayList<Shop> getAllshops() {
        return allshops;
    }

    public static void setAllshops(ArrayList<Shop> allshops) {
        Master.allshops = allshops;
    }


    static ArrayList<Shop> allshops;
    public Master(List<Worker> workers) {
        this.workers = workers;
    }


    public static void main(String args[]) throws IOException {
        System.out.println("Insert amount of workers: ");
        List<Worker> workers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int numOfWorkers=scanner.nextInt();

        for (int i = 1; i <= numOfWorkers; i++) {//create workers dynamically
            Worker worker = new Worker(i);
            workers.add(worker);
        }

        new Master(workers).openMaster();

    }


    void openMaster() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // new socket for every client
                System.out.println("New client connected");


                ClientHandler handler = new ClientHandler(clientSocket);
                new Thread(handler).start();

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    public static int H(String shopname, int numberofworkers) {//hash function based on the total number of workers
        int hash = Math.abs(shopname.hashCode());
        return hash % numberofworkers;
    }

    public static  void updateShopList(ArrayList<Shop> shops) throws IOException {
        for (Shop shop : shops) {
            int hashedid = H(shop.getShopName(), connworkers.size()) + 1;
            shop.setHashedid(hashedid);

            // find the correct worker according to hashedid
            Worker assignedWorker = null;
            for (Worker worker : workers) {
                if (worker.getWorkerid() == hashedid) {
                    assignedWorker = worker;
                    break;
                }
            }

            //update the  correct worker
            if (assignedWorker != null) {
                Request r_workers = new Request("HashShop", null, null, null, null, shop, hashedid, 0, "Worker",-1,null,null,null,null);
                ObjectOutputStream workerOut = connworkers.get(assignedWorker.getWorkerid()); //Master ---> Worker
                if (workerOut != null) {                                                      //we have a new shop, we have hashed it and now we wnt to send it to the right worker
                                                                                              //we find the correct out stream based on the hashed id
                    workerOut.writeObject(r_workers);
                    workerOut.flush();
                    System.out.println("Sent request to Worker " + assignedWorker.getWorkerid() + " for shop: " + shop.getShopName());
                } else {
                    System.out.println("Error: No connection found for Worker " + assignedWorker.getWorkerid());
                }
            } else {
                System.out.println("Error: No worker found for shop: " + shop.getShopName());
            }
        }

        }
    public static synchronized  Shop updateproductList(Shop Shop,String name,int amount) throws IOException {

            Worker assignedWorker = null;
            for (Worker worker : Master.workers) {

                if (worker.getWorkerid() ==Shop.getHashedid()) {
                    assignedWorker = worker;
                    break;
                }
            }

            if (assignedWorker != null) {
                ArrayList<Product> prlist = new ArrayList<>();
                //Master ---> Worker
                //master sends to worker the product and the amount because worker have to update also his amount
                Request r_workers = new Request("UpdateProduct", name, Shop.getShopName(),null, null, Shop, Shop.getHashedid(),amount, "Worker",-1,null,prlist,null,null);

                ObjectOutputStream workerOut = connworkers.get(assignedWorker.getWorkerid());
                if (workerOut != null) {
                    workerOut.writeObject(r_workers);
                    workerOut.flush();
                    System.out.println("Sent request to Worker " + assignedWorker.getWorkerid() + " for shop: " + Shop.getShopName());
                } else {
                    System.out.println("Error: No connection found for Worker " + assignedWorker.getWorkerid());
                }
            } else {
                System.out.println("Error: No worker found for shop: " + Shop.getShopName());
            }
            return Shop;
        }

    public static synchronized  Shop addnewproduct(Shop shop,Product product) throws IOException {
        Shop shop1=null;
        for(Shop s:shops) {
            if (s.getShopName().equals(shop.getShopName())) {

                Worker assignedWorker = null;
                for (Worker worker : Master.workers) {

                    if (worker.getWorkerid() == shop.getHashedid()) {
                        assignedWorker = worker;
                        break;
                    }
                }


                if (assignedWorker != null) {
                    // Δημιουργία του Request για τον worker
                    Request r_workers = new Request("New Product", (String) null, (String) null, product, (ArrayList<Shop>) null, shop, shop.getHashedid(), 0, "Worker", -1,null,(ArrayList<Product>) shop.getProducts(),null,null);
                    ObjectOutputStream workerOut = connworkers.get(assignedWorker.getWorkerid()); // Πάρε την σύνδεση του worker
                    if (workerOut != null) {
                        workerOut.writeObject(r_workers);  // Στείλε το Request στον worker
                        workerOut.flush();
                        System.out.println("Sent request to Worker " + assignedWorker.getWorkerid() + " for shop: " + shop.getShopName());
                    } else {
                        System.out.println("Error: No connection found for Worker " + assignedWorker.getWorkerid());
                    }
                } else {
                    System.out.println("Error: No worker found for shop: " + shop.getShopName());
                }
                s.getProducts().add(product);
               shop1=s;
            }

        }
        return shop1;
    }


    public static  synchronized void initializeShopsForWorkers(ArrayList<Shop>shops, HashMap<Integer, ObjectOutputStream> connworkers) throws IOException {
        System.out.println("Initializing shops for workers...");

        for (Shop shop : shops) {
            int hashedid = H(shop.getShopName(), connworkers.size()) + 1;
            shop.setHashedid(hashedid);  //set the hashedid to shop

            // find the correct worker
            Worker assignedWorker = null;
            for (Worker worker : workers) {
                if (worker.getWorkerid() == hashedid) {
                    assignedWorker = worker;
                    break;
                }
            }

            // update worker with the new shop
            if (assignedWorker != null) {
                Request r_workers = new Request("HashShop", null, null, null, null, shop, hashedid, 0, "Worker",-1,null,null,null,null);
                ObjectOutputStream workerOut = connworkers.get(assignedWorker.getWorkerid()); // Παίρνουμε τη σύνδεση του worker
                if (workerOut != null) {
                    //send to worker
                    workerOut.writeObject(r_workers);
                    workerOut.flush();
                    System.out.println("Sent request to Worker " + assignedWorker.getWorkerid() + " for shop: " + shop.getShopName());
                } else {
                    System.out.println("Error: No connection found for Worker " + assignedWorker.getWorkerid());
                }
            } else {
                System.out.println("Error: No worker found for shop: " + shop.getShopName());
            }
        }

    }
    public static synchronized  void unavailableproduct(Shop shop,Product product) throws IOException {

            Worker assignedWorker = null;           //finds the correct worker bosed on the hashedid
            for (Worker worker : workers) {
                if (worker.getWorkerid() == shop.getHashedid()) {
                    assignedWorker = worker;
                    break;
                }
            }


            if (assignedWorker != null) {       //Master ---> Worker
                Request r_workers = new Request("Unavailable product", null, null, product, null, shop, shop.getHashedid(), 0, "Worker",-1,null,null,null,null);
                ObjectOutputStream workerOut = connworkers.get(assignedWorker.getWorkerid());

                if (workerOut != null) {        // sends to worker the new shop with state == false

                    try {
                        workerOut.writeObject(r_workers);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    workerOut.flush();
                    System.out.println("Sent request to Worker " + assignedWorker.getWorkerid() + " for shop: " + shop.getShopName());
                } else {
                    System.out.println("Error: No connection found for Worker " + assignedWorker.getWorkerid());
                }
            } else {
                System.out.println("Error: No worker found for shop: " + shop.getShopName());
            }
        }

    public static  synchronized void addClientFilter(int clientId, Filter filter) {
        clientFilters.put(clientId, filter);

    }
    public static synchronized  void printAllClientFilters() {
        for (var entry : clientFilters.entrySet()) {

        }
    }
    public static synchronized  void addClientOutput(int clientId, ObjectOutputStream out) {
        clientOutputs.put(clientId, out);

    }




    }















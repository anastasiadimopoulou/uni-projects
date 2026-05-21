package org.example;
import android.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ActionForWorker extends Thread {

    ObjectInputStream in;
    ObjectOutputStream out;
    Request request;

    static final ArrayList<Shop> shops = new ArrayList<>();

    public ActionForWorker(ObjectInputStream in, ObjectOutputStream out, Request request) {
        this.in = in;
        this.out = out;
        this.request = request;
    }

    public void run() {
        System.out.println("ActionForWorker run() started.");

        try {
            ArrayList<Shop> allShops;
            synchronized (Master.class) {
                allShops = Master.getAllshops();
            }

            if (allShops == null) {
                System.out.println("Error: All shops list is null!");
                return;
            }

            if (request.getType().equals("Connection")) {   //Worker --->ActionForWorker
                System.out.println("connected to server");  // worker has been successfully connected to server

                synchronized (Master.class) {
                    for (Shop shop : allShops) {
                        if (shop.getHashedid() == request.getHash_worker()) {
                            System.out.println(request.getHash_worker() + " Worker id from request");

                            ObjectOutputStream out2 = Master.connworkers.get(shop.getHashedid());
                            if (out2 != null) {
                                out2.writeObject(shop);
                                out2.flush();
                            } else {
                                System.out.println("Error: out2 is null for worker " + shop.getHashedid());
                            }


                            System.out.println("Shop sent to worker");
                        }
                    }
                }

            } else if (request.getType().equals("orderlist")) {//ActionForClient ---> ActionForWorker and Worker
                synchronized (Master.class) {                  //takes the shop from the client and send it to the correct worker based on hashedid
                    ObjectOutputStream out2 = Master.connworkers.get(request.getShop().getHashedid());
                    Request r_order_w = new Request("orderlist", null, null, null, null, request.getOrder().getShop(), 0, 0, "Worker", 0, null, null, null, request.getOrder());
                    out2.writeObject(r_order_w);
                    out2.flush();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

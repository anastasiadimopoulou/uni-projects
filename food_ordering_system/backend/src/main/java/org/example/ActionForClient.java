package org.example;
import android.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ActionForClient extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
    Request request;
    Filter filter;

    public ActionForClient(ObjectInputStream in, ObjectOutputStream out, Request request, Filter filter) {
        this.in=in;
        this.out=out;
        this.request=request;
        this.filter=filter;

    }
    public void run() {
        System.out.println("ActionForClient run() started.");
        try{

            if (in != null && out != null){
                System.out.println("Received request type from User: " + request.getType());
                if (request.getType().equals("Connection")) {// client has been connect successfully to the server
                    System.out.println("Client connected and handling connection.");
                    if (request.getFilter() != null) {
                        System.out.println("Filter passed.");

                    }

                }

                else if (request.getType().equals("getOrders")) { // receives to the manager and send him the list with all orders

                    synchronized (Manager.orders) {

                        out.writeObject(Manager.orders);
                    }
                    out.flush();
                }else if(request.getType().equals("Order")){ //takes the request from the client and takes the order
                    Order order = request.getOrder();
                    Shop shop = order.getShop();
                    HashMap<Product, Integer> orderItems = order.getOrder_items();

                    // update amount
                    synchronized (shop) {
                        for (Product p : shop.getProducts()) {
                            for (Product orderedProduct : orderItems.keySet()) {
                                if (p.getProductName().equals(orderedProduct.getProductName())) {
                                    synchronized (p) {
                                        int newAmount = p.getAvailableAmount() - orderItems.get(orderedProduct);
                                        p.setAvailableAmount(newAmount);
                                    }
                                }
                            }
                        }
                    }

                    synchronized (Master.connmanagers) {
                        for (ObjectOutputStream managerOut : Master.connmanagers.values()) {
                            try {
                                Request r_order = new Request("UpdatedShop", null, null, null, null, shop, 0, 0, "Manager", 0, null, null, null, order);
                                managerOut.writeObject(r_order);//actionforclient ---> Actionformanager and manager
                                managerOut.flush();             //sends the updated shop after an order (decrease amount)

                                Request r_order_m = new Request("Order", null, null, null, null, shop, 0, 0, "Manager", 0, null, null, null, order);
                                managerOut.writeObject(r_order_m);// actionforclient creates a request order and send it to manager in order to manager  add it in the general list of orders
                                managerOut.flush();


                            } catch (IOException e) {
                                System.out.println("Failed to notify a manager: " + e.getMessage());
                            }
                        }
                    }

                    synchronized (Master.connworkers) {
                        ObjectOutputStream out2 = Master.connworkers.get(request.getShop().getHashedid());
                        Request r_order_w = new Request("orderlist", null, null, null, null, request.getOrder().getShop(), 0, 0, "Worker", 0, null, null, null, request.getOrder());
                        out2.writeObject(r_order_w);    //ActionForClient ---> ActionForWorker and Worker
                        out2.flush();                   //sends to worker the updated shop after an order
                    }

                }
                else{
                System.out.println("Error: Streams not initialized properly.");
            }





        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
        }

    }

}
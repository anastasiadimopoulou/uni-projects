package org.example;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import android.*;

public class ActionForManager extends Thread {

    ObjectInputStream in;
    ObjectOutputStream out;
    Request request;
    static ArrayList<Shop> shops = new ArrayList<>();
    Request updaterequest;

    public ActionForManager(ObjectInputStream in, ObjectOutputStream out, Request request) {
        this.in = in;
        this.out = out;
        this.request = request;
    }

    public void run() {
        if (in == null) {
            System.out.println("Error: Input stream is null. Exiting...");
            return;
        }

        synchronized (shops) {
            if (request.getType().equals("Initialize")) {   //Manager ---> ActionForManager
                shops.addAll(request.getShopList());        // takes the shops from manager and send them to workers
                try {
                    synchronized (Master.class) {
                        Master.initializeShopsForWorkers(shops, Master.connworkers);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else if (request.getType().equals("ShopList")) { //Manager ---> ActionForManager
                shops.addAll(request.getShopList());            //takes the new shop and add it to his list of shops
                ArrayList<Shop> temp = request.getShopList();
                try {
                    synchronized (Master.class) {
                        Master.updateShopList(temp);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else if (request.getType().equals("IncreaseAmount")) {//Manager ---> ActionForManager
                synchronized (this) {
                    this.updaterequest = request;
                }
            }
            else if (request.getType().equals("DecreaseAmount")) {//Manager ---> ActionForManager
                synchronized (this) {
                    this.updaterequest = request;
                }

            }
            else if (request.getType().equals("NewProductmanager")) {//Manager ---> ActionForManager
                synchronized (this) {
                    this.updaterequest = request;
                }

            }
            else if (request.getType().equals("UnavailableProduct")) {//Manager ---> ActionForManager
                synchronized (this) {
                    this.updaterequest = request;
                }

            }
           else if (request.getType().equals("UpdatedShop")) {
               // receive the request ActionForClient ---> ActionForManager
                synchronized (this) {
                    this.updaterequest = request;
                }

            }
        }


    }

    public synchronized ArrayList<Shop> getShops() {
        synchronized (shops) {

            return new ArrayList<>(shops);
        }
    }

    public synchronized Request getRequest() {
        return updaterequest;
    }
}

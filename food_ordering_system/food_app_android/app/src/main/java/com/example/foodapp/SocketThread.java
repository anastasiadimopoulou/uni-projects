package com.example.foodapp;
import android.Filter ;
import android.Order;
import android.Request;
import android.Product;
import android.Shop;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import android.os.Handler;

public class SocketThread extends Thread {

    private final String action;
    private final Filter filter;
    private final Order order;

    private final Handler handler;

    public SocketThread(String action, Filter filter, Order order, Handler handler) {
        this.action = action;
        this.filter = filter;
        this.order = order;
        this.handler = handler;
    }


    @Override
    public void run() {
        //σύνδεση με το backend
        try (Socket socket = new Socket("localhost", 4321);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            //δημιουργεί αντικείμενο Request και το στέλνει στον Master
            Request request;
            if (action.equals("Filter")) {
                request = new Request("Connection", null, null, null, null, null, 0, 0, "Client", 1, filter, null, null, null);
            } else {
                request = new Request("Order", null, null, null, null, order.getShop(), 0, 0, "Client", 1, null, null, null, order);
            }

            out.writeObject(request);
            out.flush();

            // Request από backend
            Object response = in.readObject();

            //διαχείρηση με βαση action και το request που έλαβε από το backend

            if (action.equals("Filter") && response.equals("filtershops")) {// αν απ το backend ήρθε "filtershops" και απο το action είναι "Filter" τότε στέλνουμε "1" μαζί με τα shops στο MainActivity
                ArrayList<Shop> shops = (ArrayList<Shop>) in.readObject();
                handler.sendMessage(handler.obtainMessage(1, shops));
            } else if (action.equals("Order")) {//αν το actiοn είναι "Order" (έρχεται απο το OrderSummaryActivity) τότε στέλνω "1" και μήνυμα επαλήθευσης στο OrderSummaryActivity
                handler.sendMessage(handler.obtainMessage(1, "Order placed successfully"));
            } else { //αν έρθει οτιδήποτε άλλο τοτε στέλνω "0" και μήνυμα λάθους
                handler.sendMessage(handler.obtainMessage(0, "Unexpected server response"));
            }

        } catch (Exception e) {
            handler.sendMessage(handler.obtainMessage(0, "Error: " + e.getMessage()));
        }
    }
}
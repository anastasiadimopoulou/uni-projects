package org.example;

import java.io.*;
import java.net.*;
import java.util.*;
import android.Filter;
import android.Order;
import android.Request;
import android.Shop;
import android.Product;

public class Worker extends Thread {
    private int workerid;
    private ArrayList<Shop> myshops;
    private ArrayList<Filter> processedFilters = new ArrayList<>();

    public ArrayList<Shop> getmyshops() {
        synchronized (myshops) {
            return myshops;
        }
    }

    public void setShops(ArrayList<Shop> shops) {
        synchronized (myshops) {
            this.myshops = shops;
        }
    }

    public int getWorkerid() {
        return workerid;
    }

    public void setWorkerid(int workerid) {
        this.workerid = workerid;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int port = 5000;
    int total_amount_workers;

    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    Socket socket = null;

    Socket reducerSocket = null;
    ObjectOutputStream rout = null;
    ObjectInputStream rin = null;

    Worker(int id) {
        workerid = id;
        myshops = new ArrayList<>();
        port = port + workerid;
    }

    public void run() {
        try {
            socket = new Socket("localhost", 4321);
            System.out.println("Connected to the server successfully!");

            reducerSocket = new Socket("localhost", 8080);
            System.out.println("Connected to the reducer successfully!");

            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());

            rout = new ObjectOutputStream(reducerSocket.getOutputStream());
            rout.flush();
            rin = new ObjectInputStream(reducerSocket.getInputStream());

            System.out.println("Worker Started");
            System.out.println("Worker ID: " + workerid);

            Request request = new Request("Connection", null, null, null, null, null, this.getWorkerid(), 0, "Worker", -1, null, null, null, null);
            out.writeObject(request);
            out.flush();
            System.out.println("Worker Connected to Master");

            Request rcon = new Request("Connection", null, null, null, null, null, this.getWorkerid(), 0, "Worker", this.getWorkerid(), null, null, null, null);
            rout.writeObject(rcon);
            rout.flush();
            System.out.println("Worker Connected to reducer");

            while (true) {
                try {
                    Object obj = in.readObject();

                    new Thread(() -> {
                        try {
                            if (obj instanceof Shop) {
                                Shop s = (Shop) obj;
                                System.out.println("Received shop: " + s.getShopName() + " worker id " + workerid);
                                synchronized (myshops) {
                                    myshops.add(s);
                                }
                            } else if (obj instanceof Request) {
                                Request r = (Request) obj;
                                synchronized (myshops) {
                                    switch (r.getType()) {
                                        case "HashShop":
                                            Shop s = r.getShop();
                                            System.out.println("RECEIVED hash shop: " + s.getShopName() + " worker id " + workerid);
                                            total_amount_workers = r.getProductamount();
                                            if (!myshops.contains(s)) {
                                                myshops.add(s);
                                            }
                                            for (Shop sh : getmyshops()) {
                                                updatepricecategory(sh);
                                            }
                                            break;

                                        case "NewFilters":
                                            Filter f = r.getFilter();
                                            if (f != null) {
                                                processedFilters.add(f);
                                                ArrayList<Shop> filtered_shops = filterShops(getmyshops(), f);
                                                total_amount_workers = r.getProductamount();
                                                ArrayList<Shop> deepCopiedShops = deepCopy(filtered_shops);
                                                try {
                                                    Request reducer_req = new Request("FilterShopsworkertoreducer", null, null, null, deepCopiedShops, null, -100, total_amount_workers, "Reducer", workerid, f, null, null, null);
                                                    rout.writeObject(reducer_req);
                                                    rout.flush();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            } else {
                                                System.out.println("Received null Filter object in Worker " + workerid);
                                            }
                                            break;

                                        case "UpdateProduct":
                                            Shop shop = r.getShop();
                                            String chosenproductname = r.getShopID();
                                            int amount = r.getProductamount();
                                            for (Product p1 : shop.getProducts()) {
                                                if (p1.getProductName().equals(chosenproductname)) {
                                                    p1.setAvailableAmount(p1.getAvailableAmount() + amount);
                                                }
                                            }
                                            break;

                                        case "New Product":
                                            Shop s2 = r.getShop();
                                            Product newProduct = r.getProduct();
                                            if (!s2.getProducts().contains(newProduct)) {
                                                s2.getProducts().add(newProduct);
                                            }
                                            break;

                                        case "Unavailable product":
                                            Shop s3 = r.getShop();
                                            Product unavailable = r.getProduct();
                                            for (Product p : s3.getProducts()) {
                                                if (p.getProductName().equals(unavailable.getProductName())) {
                                                    p.setAvailable(false);
                                                }
                                            }
                                            for (Shop sh : getmyshops()) {
                                                updatepricecategory(sh);
                                            }
                                            break;

                                        case "orderlist":
                                            System.out.println("request ORDERLIST on Worker " + workerid);
                                            Order order = r.getOrder();
                                            Shop orderShop = r.getShop();
                                            HashMap<Product, Integer> ordered_items = order.getOrder_items();

                                            for (Shop s4 : myshops) {
                                                if (s4.getShopName().equals(orderShop.getShopName())) {
                                                    for (Product product : ordered_items.keySet()) {
                                                        int amount_product = ordered_items.get(product);
                                                        for (Product product_worker : s4.getProducts()) {
                                                            if (product_worker.getProductName().equals(product.getProductName())) {
                                                                product_worker.setAvailableAmount(product_worker.getAvailableAmount() - amount_product);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();

                } catch (EOFException | SocketException eof) {
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepCopy(T object) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(object);
            out.flush();
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bis);
            return (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Deep copy failed", e);
        }
    }

    public static void main(String args[]) throws IOException {
        Worker w1 = new Worker(1);
        Worker w2 = new Worker(2);
        Worker w3 = new Worker(3);
        w1.start();
        w2.start();
        w3.start();
    }

    public static double distance(double client_lat1, double client_lon1, double shop_lat2, double shop_lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(shop_lat2 - client_lat1);
        double dLon = Math.toRadians(shop_lon2 - client_lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(client_lat1)) * Math.cos(Math.toRadians(shop_lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public ArrayList<Shop> filterShops(ArrayList<Shop> shops, Filter filter) {
        ArrayList<Shop> filtered_shops = new ArrayList<>();
        double filter_lat = Double.parseDouble(filter.getClientLatitude());
        double filter_lon = Double.parseDouble(filter.getClientLongitude());

        synchronized (myshops) {
            for (Shop shop : getmyshops()) {
                double d = distance(filter_lat, filter_lon, shop.getLatitude(), shop.getLongitude());
                if (d <= 5 &&
                        shop.getPricecategory().equals(filter.getPricecategory()) &&
                        shop.getStars() == Integer.parseInt(filter.getShopstars()) &&
                        shop.getFoodCategory().equals(filter.getShopcategory())) {
                    filtered_shops.add(shop);
                }
            }
        }
        return filtered_shops;
    }

    public void updatepricecategory(Shop shop) {
        double sum = 0;
        double price_cat = 0;
        synchronized (shop) {
            for (Product p : shop.getProducts()) {
                if (p.getAvailable()) {
                    sum += p.getPrice();
                }
            }
            if (shop.getProducts().size() > 0) {
                price_cat = sum / shop.getProducts().size();
            }
            if (price_cat <= 5) {
                shop.setPricecategory("$");
            } else if (price_cat <= 15) {
                shop.setPricecategory("$$");
            } else {
                shop.setPricecategory("$$$");
            }
        }
    }
}

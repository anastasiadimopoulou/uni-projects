package org.example;
import android.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Client extends Thread {
    private final String Latitude="37.9897";
    private final String Longitude="23.7260";
    private int Id =1;


    public void setId(int id) {
        Id = id;
    }




    public void printShop(Shop shop,int i){
        if (i!=0){
            System.out.println("\n==== "+ i + " ====");
        }
        System.out.println("Shop Name: " + shop.ShopName);
        System.out.println("Category: " + shop.FoodCategory);
        System.out.println("Rate: " + shop.Stars + " stars (" + shop.NoOfVotes + " number of votes)");

    }

    public void printProduct(Product product, int k ){
        if (k!=0){
            System.out.println("==== "+ k + " ====");
        }
        System.out.println("Product name: " + product.ProductName);
        System.out.println("Type: " + product.ProductType);
        System.out.println("Price: " + product.Price);
        System.out.println("Amount: "+product.getAvailableAmount());
        System.out.println("State: "+product.getAvailable());
    }


    public synchronized Order clientmenu(ArrayList<Shop>shops) {
        HashMap<Product, Integer> order_items = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        if(shops.size()>0){
        System.out.println("Based on the filters you insert the following shops are available :");
        int i = 0;
        for (Shop shop : shops) {

            i++;
            printShop(shop, i);
        }
        System.out.println("Please select one of them :");
        int chosenshopnumber = scanner.nextInt();
        Shop chosenshop = shops.get(chosenshopnumber - 1);
        System.out.println("Now select the products :");
        boolean flag = true;


        while (flag) {
            int k = 0;
            for (Product product : chosenshop.getProducts()) {
                if (product.getAvailable()) {
                    k++;
                    printProduct(product, k);
                }
            }
            System.out.println("Please select one of them :");
            int chosenproductnumber = scanner.nextInt();
            Product chosenproduct = chosenshop.getProducts().get(chosenproductnumber - 1);
            System.out.println("Please select the amount :");
            int amountofproduct = scanner.nextInt();
            while (amountofproduct > chosenproduct.getAvailableAmount()) {
                System.out.println("The amount you selected is NOT avalable");
                System.out.println("Please enter again ");
                amountofproduct = scanner.nextInt();
            }
            order_items.put(chosenproduct, amountofproduct);
            System.out.println("Do you want to add an more item ?");
            System.out.println("1-->YES   2-->NO");
            int choice = scanner.nextInt();
            if (choice == 2) {
                flag = false;
            }

        }
        Order order = new Order(chosenshop.getShopName(), order_items, chosenshop.getFoodCategory(), chosenshop);
        return order;
    }else{
            System.out.println("There is NO such shop based on your filters.");
            return null;
        }

    }

    public static void main(String[] args) {

        new Client().start();
    }



    public void run()
    {    Scanner scanner = new Scanner(System.in);

        ObjectOutputStream out=null;
        ObjectInputStream in=null;
        Socket socket=null;
        try {

            /* Create socket for contacting the server on port 4321*/
            socket = new Socket("localhost", 4321);//stsrts the communicatio with server

            /* Create the streams to send and receive data from server */

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Insert  food category");
            String shopcategory=scanner.nextLine();
            System.out.println("Insert  number of stars you want");
            String shopstars=scanner.nextLine();
            System.out.println("Insert  price category($,$$,$$$)");
            String pricecategory=scanner.nextLine();
            Filter filters=  new Filter(shopcategory,shopstars,pricecategory,Latitude,Longitude,1);

            Request r =new Request("Connection",null,null,null,null,null,0,0,"Client",1,filters,null,null,null);
            out.writeObject(r);// client create a connection with server
            out.flush();
            Order order = null;
            while (true) {
                Object response = in.readObject();

                if (response instanceof String && response.equals("filtershops")) {//ClientHandler ---> Client
                    Object shopData = in.readObject();                             // client takes the appropriate shops and starts the order

                    if (shopData instanceof ArrayList<?>) {
                        ArrayList<?> rawList = (ArrayList<?>) shopData;

                        // check if it's a list of Shop
                        if (!rawList.isEmpty() && rawList.get(0) instanceof Shop) {
                            ArrayList<Shop> shops = (ArrayList<Shop>) rawList;
                            order = clientmenu(shops);

                            if (order != null) {
                                HashMap<Product, Integer> order_items = order.getOrder_items();
                                System.out.println("=====ORDER SUMMARY======");
                                for (Product key : order_items.keySet()) {
                                    System.out.println(key.getProductName() + "  " + order_items.get(key));
                                }

                                //client creates and sends the order to actionforclient
                                Request r_order = new Request("Order", null, null, null, null,
                                        order.getShop(), 0, 0, "Client", Id, null, null, null, order);
                                out.writeObject(r_order);
                                out.flush();


                            }
                        } else {
                            System.out.println("No shops found matching your filters.");
                        }
                    } else {
                        System.out.println("Unexpected data received from server.");
                    }

                    break;
                } else {
                    System.out.println("Invalid response type from server.");
                    break;
                }
            }



        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {

                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }



}

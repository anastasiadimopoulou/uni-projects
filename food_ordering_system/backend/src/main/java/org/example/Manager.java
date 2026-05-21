package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.util.*;
import android.*;
public class Manager extends Thread {

    public static ArrayList<Shop> managershops = new ArrayList<>();

    public static List<Order> orders = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        new Manager().start();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Socket socket = null;

        try {
            ArrayList<Shop> help_shops = new ArrayList<>();
            socket = new Socket("localhost", 4321);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());

            // new thread to receive messages from the server
            ObjectInputStream finalIn = in;


            new Thread(() -> {
                try {
                    while (true) {


                        Object obj = finalIn.readObject();
                        if (obj instanceof String) {
                            String message = (String) obj;

                        } else if (obj instanceof Request request) {
                            switch (request.getType()) {
                                case "UpdatedShop" -> {
                                    Shop updatedShop = request.getShop();

                                    //update manager's shoplist
                                    synchronized (managershops) {
                                        for (int j = 0; j < managershops.size(); j++) {
                                            if (managershops.get(j).getShopName().equals(updatedShop.getShopName())) {
                                                managershops.set(j, updatedShop);
                                                break;
                                            }
                                        }
                                    }

                                }

                                case "Order" -> {

                                    //manager takes the order from the actionforclient and add it to the general list of orders
                                    Order newOrder = request.getOrder();
                                    if (newOrder != null) {
                                        Manager.orders.add(newOrder);

                                    }
                                }

                                default -> {
                                    System.out.println("Received unknown request type: " + request.getType());
                                }
                            }
                        }


                    }
                } catch (SocketException e) {
                    System.out.println("Exit...");
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();

            readJson("shop.json");


            Request r_init = new Request("Initialize",  null, null, null,  managershops, null, 0, 0, "Manager", -1, null, null, null, null);
            out.writeObject(r_init);    //Manager ---> ActionForManager
            out.flush();                //initialize the basic 4 shops from shops.json to manager's list

            int choice = this.printmenu();


            //Manager's menu
            while (choice != 5) {


                if (choice == 1) {
                    System.out.println("Insert the name of the file: ");
                    String shopfilename = scanner.nextLine();
                    help_shops = this.readJson(shopfilename);

                    Request re = new Request("ShopList", null, null, null,help_shops, null, 0, 0, "Manager", -1, null, null, null, null);
                    out.writeObject(re);        //Manager ---> ActionForManager
                    out.flush();                //choice 1 from manager's menu (add a new shop from  .json file)

                }  else if (choice == 2) {
                    int i = 1;
                    synchronized (managershops) {
                        for (Shop shop : managershops) {
                            printShop(shop, i);
                            i++;
                        }
                    }


                    System.out.println("Insert the number of the shop");
                    int shopNumber = scanner.nextInt();
                    Shop chosenShop;
                    synchronized (managershops) {
                        chosenShop = managershops.get(shopNumber - 1);
                    }
                    int k = 1;
                    for (Product product : chosenShop.Products) {
                        if (product.getAvailable()) {
                            printProduct(product, k);
                            k++;
                        }
                    }

                    System.out.println("Insert the number of the product");
                    int productNumber = scanner.nextInt();

                    Product chosenProduct = chosenShop.getProducts().get(productNumber - 1);
                    System.out.println("Press 1 if you want to increase the amount or 2 if you want to decrease it.");
                    int choice2 = scanner.nextInt();

                    if (choice2 == 1) {
                        System.out.println("Insert the amount you want to increase.");
                        int amount = scanner.nextInt();

                        synchronized (chosenProduct) {
                            chosenProduct.setAvailableAmount(chosenProduct.getAvailableAmount() + amount);
                        }

                        Request request = new Request("IncreaseAmount", null, chosenShop.getShopName(), chosenProduct, null, chosenShop, 0, amount, "Manager", -1, null, null, null, null);
                        out.writeObject(request); //Manager ---> ClientHandler
                        out.flush();              //manager sends to clienthandler the product,the shop and the amount in order to do the update  (+) in the amount




                    } else if (choice2 == 2) {
                        System.out.println("Insert the amount for decrease");
                        int amount = scanner.nextInt();

                        synchronized (chosenProduct) {
                            chosenProduct.setAvailableAmount(chosenProduct.getAvailableAmount() - amount);
                        }

                        Request request = new Request("DecreaseAmount", chosenShop.getShopName(), chosenShop.getShopName(), chosenProduct, null, chosenShop, 0, amount * (-1), "Manager", -1, null, null, null, null);
                        out.writeObject(request); //Manager ---> ClientHandler
                        out.flush();             //manager sends to clienthandler the product,the shop and the amount in order to do the update  (-) in the amount





                    }
                    if(in.available()>0){
                        // updated shop
                        Object response = in.readObject();
                        if (response instanceof String && response.equals("UpdatedShop")) {
                            //receive the string from clienthandler ---> Manager
                            //takes the updated shop after an order and replace it in manager's list with all the shop because now the amount's are now updated
                            Shop updatedShop = (Shop) in.readObject();



                            // replace the old shop
                            synchronized (managershops) {
                                for (int j = 0; j < managershops.size(); j++) {
                                    if (managershops.get(j).getShopName().equals(updatedShop.getShopName())) {
                                        managershops.set(j, updatedShop);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                else if (choice == 3) {

                    int i = 1;
                    synchronized (managershops) {
                        for (Shop shop : managershops) {
                            this.printShop(shop, i);
                            i++;
                        }
                    }
                    System.out.println("Insert the number of the shop");
                    int shopNumber = scanner.nextInt();

                    Shop chosenShop;
                    synchronized (managershops) {
                        chosenShop = managershops.get(shopNumber - 1);
                    }
                    System.out.println("Press 1 if you want to add a new product or 2 if you want to remove an old one.");
                    int choice2 = scanner.nextInt();

                    if (choice2 == 1) {
                        Product newproduct = this.createNewProduct();
                        chosenShop.Products.add(newproduct);

                        Request request = new Request("NewProductmanager", null, chosenShop.getShopName(), newproduct, null, null, 0, 0, "Manager", -1, null, null, null, null);

                        out.writeObject(request); //Manager ---> ClientHandler
                        out.flush();             //manager sends to clienthandler the product,the shop and the amount in order to add it in the list









                    } else if (choice2 == 2) {
                        int k = 1;
                        for (Product product : chosenShop.Products){
                            if (product.getAvailable()) {
                                this.printProduct(product, k);
                                k++;
                            }
                        }

                        System.out.println("Insert the number of the product");
                        int productNumber = scanner.nextInt();

                        Product chosenProduct = chosenShop.getProducts().get(productNumber - 1);
                        synchronized (chosenProduct) {
                            chosenProduct.setAvailable(false);
                        }

                        Request request = new Request("UnavailableProduct", null, chosenShop.getShopName(), chosenProduct, null, null, 0, 0, "Manager", -1, null, null, null, null);
                        out.writeObject(request);           //Manager ---> ClientHandler
                        out.flush();                        //manager sends to clienthandler the product and the shop with state == false


                    }

                    if(in.available()>0){
                        // updated shop
                        Object response = in.readObject();
                        if (response instanceof String && response.equals("UpdatedShop")) {
                            Shop updatedShop = (Shop) in.readObject();



                            // replace old Shop
                            synchronized (managershops) {
                                for (int j = 0; j < managershops.size(); j++) {
                                    if (managershops.get(j).getShopName().equals(updatedShop.getShopName())) {
                                        managershops.set(j, updatedShop);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                else if (choice == 4) {

                    //Request request = new Request("getOrders",null,null,null,null,null,0,0,"Client",0,null,null,null,null); // φτιάξε έναν τύπο request που ζητά τις παραγγελίες
                    //out.writeObject(request); // manager request the order's list from ActionForClient in order to makes the queries
                    //out.flush();


                    System.out.println("Insert 1 for FoodCategory");
                    System.out.println("Insert 2 for ProductCategory");

                    int numberquery = scanner.nextInt();
                    if (numberquery == 1) {
                        int total=0;
                        System.out.println("Insert FoodCategory");
                        Scanner sc = new Scanner(System.in);
                        String foodcat = sc.nextLine();
                        HashMap<String,Integer> shops_counter=new HashMap<>();

                        if(Manager.orders!=null){
                            for(Order order : Manager.orders) {
                                String foodcategory_order=order.getFoodcategory();
                                if(foodcategory_order.equals(foodcat)) {
                                    total++;
                                    if (!shops_counter.containsKey(order.getShopname())) {
                                        shops_counter.put(order.getShopname(), 1);
                                    } else {
                                        shops_counter.put(order.getShopname(), shops_counter.get(order.getShopname()) + 1);
                                    }
                                }
                            }

                            for (Map.Entry<String, Integer> entry : shops_counter.entrySet()) {

                                System.out.println(entry.getKey() + ": " + entry.getValue());
                            }

                            System.out.println("total: " + total);

                        }else{break;}
                    }

                    else if (numberquery == 2) {
                        System.out.println("Insert ProductCategory:");
                        Scanner sc = new Scanner(System.in);
                        String productcat = sc.nextLine();
                        HashMap<String, Integer> shops_counter = new HashMap<>();
                        int totalMatching = 0;
                        int totalNonMatching = 0;
                        if(Manager.orders!=null) {
                            for (Order order : Manager.orders) {
                                HashMap<Product, Integer> products_order = order.getOrder_items();
                                String shopName = order.getShopname();

                                for (Map.Entry<Product, Integer> entry : products_order.entrySet()) {
                                    Product product = entry.getKey();
                                    int quantity = entry.getValue();

                                    if (product.getProductType().equals(productcat)) {

                                        shops_counter.put(shopName, shops_counter.getOrDefault(shopName, 0) + quantity);
                                        totalMatching += quantity;
                                    } else {
                                        totalNonMatching += quantity;
                                    }
                                }
                            }


                            for (Map.Entry<String, Integer> entry : shops_counter.entrySet()) {
                                System.out.println(entry.getKey() + ": " + entry.getValue());
                            }
                            System.out.println("total: " + (totalMatching + totalNonMatching));
                        }
                    }

                }



                choice = this.printmenu();
            }
            out.flush();
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

    public int printmenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---------MANAGER'S MENU-------");
        System.out.println("1. Add a new shop");
        System.out.println("2. Select a shop to add/remove an available product");
        System.out.println("3. Select a shop to add/remove a new product");
        System.out.println("4. Print the total sales of a product");
        System.out.println("5. Exit");
        System.out.println("Choose an option from 1 to 5: ");
        int choice = scanner.nextInt();
        System.out.println("-------------------------------");

        return choice;
    }

    public ArrayList<Shop> readJson(String filename) {
        ArrayList<Shop> allShops = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("files/" + filename)) {
            if (inputStream == null) {
                System.out.println("File " + filename + " was not found!");
            }

            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode shopsNode = rootNode.get("Shops");

            if (shopsNode == null || !shopsNode.isArray()) {
                System.out.println("'Shops' is either not found or not a list!");
            }

            for (JsonNode shopNode : shopsNode) {
                Shop shop = objectMapper.treeToValue(shopNode, Shop.class);
                allShops.add(shop);
                managershops.add(shop);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return allShops;
    }

    public void printShop(Shop shop, int i) {
        if (i != 0) {
            System.out.println("\n==== " + i + " ====");
        }
        System.out.println("Shop Name: " + shop.ShopName);
        System.out.println("Location: (" + shop.Latitude + ", " + shop.Longitude + ")");
        System.out.println("Category: " + shop.FoodCategory);
        System.out.println("Rate: " + shop.Stars + " stars (" + shop.NoOfVotes + " number of votes)");
    }

    public void printProduct(Product product, int k) {
        if (k != 0) {
            System.out.println("==== " + k + " ====");
        }
        System.out.println("Product name: " + product.ProductName);
        System.out.println("Type: " + product.ProductType);
        System.out.println("Price: " + product.Price);
        System.out.println("Available amount: " + product.AvailableAmount);
    }

    public Product createNewProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert the name of the product");
        String productName = scanner.nextLine();
        System.out.println("Insert the type of the product");
        String productType = scanner.nextLine();
        System.out.println("Insert the amount that is available");
        int productAvailableAmount = scanner.nextInt();
        while (productAvailableAmount < 0) {
            System.out.println("Invalid amount please insert 0 or a positive number");
            productAvailableAmount = scanner.nextInt();
        }
        System.out.println("Insert the price of the product");
        double productPrice = scanner.nextDouble();
        while (productPrice < 0) {
            System.out.println("Invalid amount please insert 0 or a positive number");
            productPrice = scanner.nextDouble();
        }

        Product newProduct = new Product(productName, productType, productAvailableAmount, productPrice);

        return newProduct;
    }
}

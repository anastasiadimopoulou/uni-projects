package android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Request  implements Serializable {
    String type;
    String shopID;
    String shopName;
    Product Product;
    Filter Filter;
    ArrayList<Product> productList;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    ArrayList<Order> orders;
    Order order;


    public int getHash_worker() {
        return hash_worker;
    }

    public void setHash_worker(int hash_worker) {
        this.hash_worker = hash_worker;
    }

    int hash_worker;
    Shop shop;
    String clientType;
    int productamount;
    int ID;

    public void setShopList(ArrayList<Shop> shopList) {
        this.shopList = shopList;
    }

    ArrayList<Shop> shopList;

    public Shop getShop() {
        return shop;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Filter getFilter() {
        return Filter;
    }

    public void setFilter(Filter filter) {
        Filter = filter;
    }

    public Request(String type, String shopID, String shopName, Product Product, ArrayList<Shop> shopList, Shop shop, int hash_worker, int productamount , String clientType, int  ID, Filter Filter,ArrayList<Product> productList,ArrayList<Order> orders, Order order    ) {
        this.type = type;
        this.shopID = shopID;
        this.shopName = shopName;
        this.Product = Product;
        this.shopList = shopList;
        this.shop=shop;
        this.hash_worker=hash_worker;
        this.productamount=productamount;
        this.clientType=clientType;
        this.ID=ID;
        this.Filter=Filter;
        this.productList=productList;
        this.orders=orders;
        this.order=order;


    }
    public String getType() {
        return type;

    }

    public int getProductamount() {return productamount;}
    public void setProductamount(int productamount) {this.productamount = productamount;}
    public String getShopID() {
        return shopID;
    }
    public String getShopName() {
        return shopName;
    }

    public Product getProduct() {
        return Product;

    }
    public ArrayList<Shop> getShopList() {
        return shopList;
    }


    public String getClientType() {
        return clientType;
    }

    public List<Product> getproductlist() {

        return productList;

    }
}

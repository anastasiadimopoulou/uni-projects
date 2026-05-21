package android;

import java.io.Serializable;
import java.util.HashMap;

public class Order implements Serializable {
    String shopname;
    HashMap<Product,Integer> order_items;
    String foodcategory;

    public synchronized  Shop getShop() {
        return shop;
    }

    public  synchronized void setShop(Shop shop) {
        this.shop = shop;
    }

    Shop shop;

    public Order(String shopname,HashMap<Product,Integer> order_items,String foodcategory,Shop  shop) {
        this.shopname=shopname;
        this.order_items=order_items;
        this.foodcategory=foodcategory;
        this.shop=shop;
    }

    public  synchronized String getShopname() {
        return shopname;
    }

    public  synchronized void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public   synchronized HashMap<Product, Integer> getOrder_items() {
        return order_items;
    }

    public  synchronized void setOrder_items(HashMap<Product, Integer> order_items) {
        this.order_items = order_items;
    }

    public  String getFoodcategory() {
        return foodcategory;
    }

    public void setFoodcategory(String foodcategory) {
        this.foodcategory = foodcategory;
    }
}

package android;

import java.io.Serializable;

public class Product  implements Serializable {
    public String ProductName;
    public String ProductType;
    public int AvailableAmount;
    public double Price;
    public boolean available=true;;

    public  Product(String ProductName, String ProductType, int AvailableAmount, double Price) {
        this.ProductName = ProductName;
        this.ProductType = ProductType;
        this.AvailableAmount = AvailableAmount;
        this.Price = Price;


    }
    public Product() {
    }

    public Product(String ProductName, int AvailableAmount, double Price) {}
    public synchronized  void notavailable(){
        this.available=false;
    }
    public synchronized  String getProductType() {
        return ProductType;
    }

    public synchronized  String getProductName() {
        return ProductName;
    }

    public synchronized  int getAvailableAmount() {
        return this.AvailableAmount;
    }

    public synchronized  double getPrice() {
        return Price;
    }

    public synchronized  boolean getAvailable(){
        return available;
    }

    public synchronized  void setProductName(String productName) {
        ProductName = productName;
    }

    public  synchronized void setProductType(String productType) {
        ProductType = productType;
    }

    public  synchronized void setAvailableAmount(int availableAmount) {
        this.AvailableAmount = availableAmount;
    }

    public synchronized  void setPrice(double price) {
        Price = price;
    }

    public synchronized  void setAvailable(boolean Available){
        available= Available;
    }
}

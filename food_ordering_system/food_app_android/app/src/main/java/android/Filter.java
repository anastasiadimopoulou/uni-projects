package android;

import java.io.Serializable;

public class Filter  implements Serializable {
    private String shopcategory;
    private String shopstars;
    private String pricecategory;
    private String clientLatitude;

    public   int getClientid() {
        return clientid;
    }

    public   void setClientid(int clientid) {
        this.clientid = clientid;
    }

    private int clientid;

    public   String getClientLongitude() {
        return clientLongitude;
    }

    public  synchronized void setClientLongitude(String clientLongitude) {
        this.clientLongitude = clientLongitude;
    }

    public  synchronized String getClientLatitude() {
        return clientLatitude;
    }

    public  void setClientLatitude(String clientLatitude) {
        this.clientLatitude = clientLatitude;
    }

    private String clientLongitude;



    public Filter(String shopcategory, String shopstars, String pricecategory, String clientLatitude, String clientLongitude,int clientid) {
        this.shopcategory = shopcategory;
        this.shopstars = shopstars;
        this.pricecategory = pricecategory;
        this.clientLatitude = clientLatitude;
        this.clientLongitude = clientLongitude;
        this.clientid = clientid;

    }
    public String getShopcategory() {
        return shopcategory;

    }
    public synchronized  void setShopcategory(String shopcategory) {
        this.shopcategory = shopcategory;
    }
    public String getShopstars() {
        return shopstars;
    }
    public synchronized  void setShopstars(String shopstars) {
        this.shopstars = shopstars;
    }
    public String getPricecategory() {
        return pricecategory;
    }
    public synchronized  void setPricecategory(String pricecategory) {
        this.pricecategory = pricecategory;
    }

    @Override
    public String toString() {
        return "Filter [shopcategory=" + shopcategory + ", shopstars=" + shopstars + ", pricecategory=" + pricecategory + ", clientLatitude=" + clientLatitude + ", clientLongitude=" + clientLongitude ;
    }
}
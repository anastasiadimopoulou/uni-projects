package android;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Serializable {

    public String ShopName;
    public double Latitude;
    public double Longitude;
    public String FoodCategory;

    public synchronized String getPricecategory() {
        return pricecategory;
    }

    public synchronized  void setPricecategory(String pricecategory) {
        this.pricecategory = pricecategory;
    }

    public String pricecategory;
    public int Stars;
    public int NoOfVotes;
    public String ShopLogo;
    public List<Product> Products;
    public int hashedid;
    public int getHashedid() {
        return hashedid;
    }

    public synchronized  void setHashedid(int hashedid) {
        this.hashedid = hashedid;
    }



    public synchronized  String getShopName() {
        return ShopName;
    }

    public synchronized  void setShopName(String shopName) {
        ShopName = shopName;
    }

    public synchronized  double getLatitude() {
        return Latitude;
    }

    public synchronized  void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public synchronized  double getLongitude() {
        return Longitude;
    }

    public synchronized  void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public synchronized  String getFoodCategory() {
        return FoodCategory;
    }

    public synchronized  void setFoodCategory(String foodCategory) {
        FoodCategory = foodCategory;
    }

    public synchronized  int getStars() {
        return Stars;
    }

    public synchronized  void setStars(int stars) {
        Stars = stars;
    }

    public synchronized  int getNoOfVotes() {
        return NoOfVotes;
    }

    public synchronized  void setNoOfVotes(int noOfVotes) {
        NoOfVotes = noOfVotes;
    }

    public  synchronized List<Product> getProducts() {
        return Products;
    }

    public  synchronized void setProducts(List<Product> products) {
        Products = products;
    }

    public  synchronized String getShopLogo() {
        return ShopLogo;
    }

    public  synchronized void setShopLogo(String shopLogo) {
        ShopLogo = shopLogo;
    }

    public Shop() {
    }

    public Shop(String ShopName, double Latitude, double Longitude, String FoodCategory, int Stars, int NoOfVotes, String ShopLogo,int hid) {
        this.ShopName = ShopName;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.FoodCategory = FoodCategory;
        this.Stars = Stars;
        this.NoOfVotes = NoOfVotes;
        this.ShopLogo = ShopLogo;
        this.Products = new ArrayList<Product>();
        this.hashedid=hid;
    }
}

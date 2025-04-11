package model;

public class price {

    private double price;

    public price(double price) {
        this.price = price;
    }

    public double getPrice(double discount) {
        return price - (price * discount);
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

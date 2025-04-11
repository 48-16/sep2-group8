package model;

public class product {

    private int id;
    private String productName;
    private price price;
    private int quantity;

    public product(int id,
                   String productName,
                   price price,
                   int quantity) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice(double discount) {
        return price.getPrice(discount);
    }

    public void setPrice(double price) {
        this.price.setPrice(price);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

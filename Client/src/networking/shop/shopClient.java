package networking.shop;

import model.product;
import java.util.List;
import java.util.Map;

public class shopClient {
    public shopClient() {
    }

    public List<product> getAllProducts() {
        // TODO: Implement actual network call to get products
        return List.of();
    }

    public void updateProductStock(String productName, int newQuantity) {
        // TODO: Implement actual network call to update product stock
    }

    public void savePurchase(Map<String, Integer> cart) {
        // TODO: Implement actual network call to save purchase
    }

    public void addNewProduct(product newProduct) {
        // TODO: Implement actual network call to add new product
    }
} 
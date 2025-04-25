package ui.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import networking.shop.shopClient;
import utils.ErrorPopUp;
import model.product;
import model.price;

import java.util.HashMap;
import java.util.Map;

public class ShopModel
{
  private final ObservableList<product> products = FXCollections.observableArrayList();
  private final Map<String, Integer> cart = new HashMap<>();
  private final ErrorPopUp errorPopUp = new ErrorPopUp();
  private final shopClient shopService;

  public ShopModel(shopClient shopClient)
  {
    this.shopService = shopClient;
    loadProducts();
  }

  private void loadProducts()
  {
    products.setAll(shopService.getAllProducts());
  }

  public ObservableList<product> getProducts()
  {
    return products;
  }

  public void addToCart(String productName, int quantity)
  {
    if (quantity <= 0)
    {
      errorPopUp.show("Error", "Quantity must be greater than 0");
      return;
    }

    product product = findProduct(productName);
    if (product == null)
    {
      errorPopUp.show("Error", "Product not found");
      return;
    }

    if (product.getQuantity() < quantity)
    {
      errorPopUp.show("Error", "Not enough stock available");
      return;
    }

    cart.merge(productName, quantity, Integer::sum);
    product.setQuantity(product.getQuantity() - quantity);
    shopService.updateProductStock(productName, product.getQuantity());
  }

  public void confirmPurchase()
  {
    if (cart.isEmpty())
    {
      errorPopUp.show("Error", "Cart is empty");
      return;
    }

    shopService.savePurchase(cart);
    cart.clear();
    loadProducts(); // Refresh product list after purchase
    errorPopUp.show("Success", "Purchase completed successfully!");
  }

  public void addNewProduct(String name, int quantity)
  {
    if (quantity <= 0)
    {
      errorPopUp.show("Error", "Quantity must be greater than 0");
      return;
    }

    product newProduct = new product(0, name, new price(0.0), quantity);
    shopService.addNewProduct(newProduct);
    loadProducts(); // Refresh product list
    errorPopUp.show("Success", "Product added successfully!");
  }

  public Map<String, Integer> getCart()
  {
    return cart;
  }

  private product findProduct(String name)
  {
    return products.stream().filter(p -> p.getProductName().equals(name)).findFirst()
        .orElse(null);
  }
} 
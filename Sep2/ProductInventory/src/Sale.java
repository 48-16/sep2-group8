import java.time.LocalDateTime;

public class Sale
{
  private Product product;
  private int quantitySold;
  private double totalPrice;


  public Sale(Product product, int quantitySold, double totalPrice) {
    this.product = product;
    this.quantitySold = quantitySold;
    this.totalPrice = totalPrice;
  }

  public double getTotalPrice()
  {
    return totalPrice;
  }

  public String toString() {
    return product.getName() + "\n" +
          "Quantity: " + quantitySold + " \n"
          + "Total: " +  totalPrice + "\n";
  }
}

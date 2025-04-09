import java.time.LocalDateTime;

public class Sale
{
  private Product product;
  private int quantitySold;
  private double totalPrice;
  private LocalDateTime saleTime;

  public Sale(Product product, int quantitySold, double totalPrice, LocalDateTime saleTime) {
    this.product = product;
    this.quantitySold = quantitySold;
    this.totalPrice = totalPrice;
    this.saleTime = LocalDateTime.now(); // or this.saleTime = saleTime; not sure;
  }

  public double getTotalPrice()
  {
    return totalPrice;
  }

  public String toString() {
    return product.getName() + "\n" +
          "Quantity: " + quantitySold + " \n"
          + "Total: " +  totalPrice + "\n" +
           "Date: " + saleTime;
  }
}

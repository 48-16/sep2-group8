import java.util.ArrayList;

public class Revenue
{

  private ArrayList<Sale> sales;

  public Revenue() {
    sales = new ArrayList<>();
  }

  public void addSale(Sale sale) {
    sales.add(sale);
  }

  public void removeSale(Sale sale) {
    sales.remove(sale);
  } // in case of mistake


  public double calculateRevenue() {
   double totalRevenue = 0.0;
    for(Sale sale : sales) {
     totalRevenue =  totalRevenue + sale.getTotalPrice();
    }
    return totalRevenue;
  }

  }


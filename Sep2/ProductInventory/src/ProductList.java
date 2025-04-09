import java.util.ArrayList;

public class ProductList
{
  private ArrayList<Product> products;

  public ProductList()
  {
    products = new ArrayList<>();
  }

  public void addProduct(Product product)
  {
    products.add(product);
  }

  public void removeProduct(Product product) {
    products.remove(product);
  }

  public void changeQuantity (String id, int quantityDifference) {
    for (Product product : products)
    {
      if( product.getId().equals(id))
      {
        product.setQuantity(product.getQuantity() + quantityDifference);
      }
      if (product.getQuantity() + quantityDifference >= 0) {
        product.setQuantity(product.getQuantity() + quantityDifference);
      }
    }
  }

}

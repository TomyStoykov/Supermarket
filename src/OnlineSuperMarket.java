import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OnlineSuperMarket {
    List<Product> products;

    public OnlineSuperMarket(List<Product> products) {
        products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void printProducts(){
        System.out.println("Printing the list of products: ");
        for(Product product : products){
            System.out.println(product.getProduct() + product.getCategory() + product.getPrice());
        }
    }
    public void sortPrices(){
        Collections.sort(products, Comparator.comparing(Product::getPrice));
    }
    public void sortCategory(String category){
    List<Product> sortedProducts = new ArrayList<>();
    for(Product product : products){
        if(product.getCategory().equals(category)){
            sortedProducts.add(product);
        }
    }
    }
}

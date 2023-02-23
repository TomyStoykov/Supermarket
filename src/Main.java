import java.io.Serializable;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        OnlineSuperMarket onlineSuperMarket = new OnlineSuperMarket(10000);
        Product p1 = new Product("Milk ", "Dairy ", 2.5);
        Product p2 = new Product("Eggs ", "Dairy ", 1.5);
        Product p3 = new Product("Bread ", "Bakery ", 1.0);
        Product p4 = new Product("Apple ", "Fruit ", 0.5);
        Product p5 = new Product("Orange ", "Fruit ", 0.8);
        onlineSuperMarket.addProduct(p1);
        onlineSuperMarket.addProduct(p2);
        onlineSuperMarket.addProduct(p3);
        onlineSuperMarket.addProduct(p4);
        onlineSuperMarket.addProduct(p5);
        onlineSuperMarket.printProducts();
        onlineSuperMarket.sortCategory("Dairy");
    }
}
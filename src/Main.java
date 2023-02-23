

public class Main {
    public static void main(String[] args) {
        OnlineSuperMarket onlineSuperMarket = new OnlineSuperMarket(5);
        Product products[] = {new Product("Milk", "Dairy", 2.5),
        new Product("Eggs", "Dairy", 1.5),
        new Product("Bread", "Bakery", 1.0),
        new Product("Apple", "Fruit", 0.5),
        new Product("Orange", "Fruit", 0.8)
        };
        for(Product product : products) {
            onlineSuperMarket.addProduct(product);
        }
        onlineSuperMarket.printProducts();
        onlineSuperMarket.sortCategory("Fruit");
        onlineSuperMarket.sortPrices();
        onlineSuperMarket.shoppingCart("Eggs");
        onlineSuperMarket.shoppingCart("Bread");
        onlineSuperMarket.checkout();
    }
}
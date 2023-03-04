import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OnlineSuperMarket onlineSuperMarket = new OnlineSuperMarket(55);
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
        onlineSuperMarket.removeProduct("Eggs");
        onlineSuperMarket.filterCategory("Fruit");
        onlineSuperMarket.sortCategories();
        onlineSuperMarket.sortPrices();
        onlineSuperMarket.shoppingCart("Apple");
        onlineSuperMarket.shoppingCart("Bread");
        System.out.println("In order to continue to checkout you need to be signed in, if you don't have an account enter 1 to register or else 2 to log in");
        int choice  = scanner.nextInt();
        if(choice ==  1){
            UserFactory.register();
            System.out.println("Log in to continue");
            UserFactory.logIn();
        }else if(choice == 2){
            UserFactory.logIn();
        }else {
            System.out.println("You have entered an invalid option");
            System.exit(0);
        }
        if(UserFactory.loggedIn){
            onlineSuperMarket.checkout();
        }else {
            System.out.println("You need to be logged in to be able to use checkout");
        }
    }
}
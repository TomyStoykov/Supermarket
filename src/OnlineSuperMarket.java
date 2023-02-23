import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OnlineSuperMarket implements Serializable {
    private List<Product> products;
    List<Product> shoppingCartArr;
    private double balance;
    private final String fileName = "db.bin";

    public OnlineSuperMarket(double balance) {
        this.balance = balance;
        products = new ArrayList<>();
        shoppingCartArr = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addProduct(Product product){
        products.add(product);
        saveData();
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
    for(Product sortedProduct : sortedProducts){
        System.out.println(sortedProduct.getProduct());
    }
    }
    public void shoppingCart(String productName){
        Product product = findProduct(productName);
    if(product == null){
        System.out.println("Product not found");
    }
    else if (getBalance() - product.getPrice() < 0){
            System.out.println("Insufficient balance");
        }
    else{
        shoppingCartArr.add(product);
        System.out.println("Product added to the cart:" + product.getProduct());
        balance = getBalance() - product.getPrice();
        System.out.println("Remaining balance: " + balance);
        saveData();
    }
    }
    public Product findProduct(String product){
        for (Product product1 : products){
            if(product1.getProduct().equals(product)){
                return product1;
            }
        }
        return null;
    }

    private void saveData(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(products);
            out.writeDouble(balance);
            out.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void loadData(){
        try{
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            products = (List<Product>) in.readObject();
            balance = in.readDouble();
            in.close();
            fileInputStream.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private void checkout(){
        if(shoppingCartArr.isEmpty()){
            System.out.println("There is nothing in the shopping cart");
            return;
        }
        double total = 0;
        for(Product product : shoppingCartArr){
            total += product.getPrice();
            System.out.println("Product : " + product.getProduct() + " = " + product.getPrice());
        }
        System.out.println("Total cost is : " +total);
        double remainingBalance = getBalance() - total;
        System.out.println("Money left: " +remainingBalance);
        if(remainingBalance < 0){
            System.out.println("Insufficient balance");
        }
        try{
            String file = "checkout_receipt.txt";
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Items:");
            for(Product product : shoppingCartArr){
                fileWriter.write(product.getProduct() + " = " + product.getPrice() + "\n");
            }
            fileWriter.write("Total is: "+total);
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

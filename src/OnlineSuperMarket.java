import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class OnlineSuperMarket implements Serializable{
    private List<Product> products;
    private List<Product> shoppingCartArr;
    private double balance;

    public OnlineSuperMarket(double balance) {
        this.balance = balance;
        products = new ArrayList<>();
        shoppingCartArr = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }


    public void addProduct(Product product){
        products.add(product);
        ReadAndWrite.saveData(products,balance);
    }
    public void removeProduct(String product){
        for(int i = 0;i< products.size();i++){
            if (products.get(i).getProduct().equals(product)) {
                System.out.println("Product removed: "+products.get(i).getProduct());
                products.remove(i);
                return;
            }
        }
        System.out.println("Product not found");
    }
    public void printProducts(){
        for(Product product : products){
            System.out.println(product.getProduct()+ " " + product.getCategory() +" "+ product.getPrice());
        }
    }
    public void sortPrices(){
        products.sort(Comparator.comparing(Product::getPrice));
        System.out.println("Prices sorted");
        printProducts();
    }

    public void sortCategories(){
        products.sort(Comparator.comparing(Product::getProduct));
        System.out.println("Products sorted: ");
        printProducts();
    }

    public void filterCategory(String category){
        List<Product> sortedProducts = new ArrayList<>();

        for(Product product : products){
            String currProductCategory = product.getCategory();

            if(currProductCategory.equals(category)){
                sortedProducts.add(product);
            }
        }

        if(sortedProducts.isEmpty()){
            System.out.println("No products in that category");
        }
        else{
            System.out.println("Products in category " + category + ": ");
            for (Product sortedProduct : sortedProducts){
                System.out.println(sortedProduct.getProduct() +" "+ sortedProduct.getPrice());
            }
        }
    }
    public void shoppingCart(String productName){
        Product product = findProduct(productName);
    if(product == null){
        System.out.println("Product not found");
    }
    else if (getBalance() - product.getPrice() < 0){
            System.out.println("Insufficient balance, " + product.getProduct() + " wasn't added to the cart" );
        }
    else{
        shoppingCartArr.add(product);
        System.out.println("Product added to the cart:" + product.getProduct());
        balance = getBalance() - product.getPrice();
        System.out.println("Remaining balance: $" + balance);
        ReadAndWrite.saveData(products,balance);
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

    public void checkout(){
        if(shoppingCartArr.isEmpty()){
            System.out.println("There is nothing in the shopping cart");
            return;
        }
        double total = 0;
        for(Product product : shoppingCartArr){
            total += product.getPrice();
            System.out.println("Product : " + product.getProduct() + " = $" + product.getPrice());
        }
        System.out.println("Total cost is : $" +total);
        //double remainingBalance = getBalance() - total;
        System.out.println("Money left: $" +getBalance());
        if(getBalance() < 0){
            System.out.println("Insufficient balance");
        }
        try{
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String file = "checkout_receipt.txt";
            FileWriter fileWriter = new FileWriter(file,true);
            fileWriter.write("\nDate: "+simpleDateFormat.format(new Date()) + "\n");
            fileWriter.write("Items:");
            for(Product product : shoppingCartArr){
                fileWriter.write(product.getProduct() + " = $" + product.getPrice() + "\n");
            }
            fileWriter.write("Total is: $"+total +"\n");
            fileWriter.write("///////////////////////////////////");
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

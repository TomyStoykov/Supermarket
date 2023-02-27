import java.io.*;
import java.util.List;



public class ReadAndWrite{
    public static final String fileName = "db.bin";
        public static void saveData(List<Product> products,double balance){
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
    public static void loadData(List<Product> products,double balance){
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


}

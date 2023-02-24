import java.io.*;
import java.util.Scanner;

public class validateUser {

    public  void register(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String userName = scanner.nextLine();
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        System.out.println("Enter your bank account");
        String bankAccount = scanner.nextLine();
        System.out.println("Account successfully created");
        try{
            FileWriter writer = new FileWriter(userName + ".txt");
            writer.write("Username: "+ userName + "\n");
            writer.write("Password: "+ password + "\n");
            writer.write("Email: "+ email + "\n");
            writer.write("Bank Acoount: "+ bankAccount + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void logIn() throws  IOException {
        Scanner scanner = new Scanner(System.in);
        boolean isValidated = false;
        String userName;
        String password;
        System.out.println("Enter username or email: ");
        userName = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();
        File file = new File(userName + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String storedUserName = reader.readLine();
        String storedEmail = reader.readLine();
        String storedPassword = reader.readLine();
        String storedBankAccount = reader.readLine();
        reader.close();
        if (userName.equals(storedUserName) && password.equals(storedPassword)) ;
        {
            isValidated = true;
        }
    }

}

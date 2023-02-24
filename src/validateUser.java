import java.io.*;
import java.util.Scanner;

public class validateUser {
    /*private String userName;
    private String email;
    private String password;
    private String bankAccount;

    Scanner scanner = new Scanner(System.in);

    public validateUser(String userName, String email, String password, String bankAccount) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.bankAccount = bankAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }*/
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

import java.util.Scanner;

public class validateUser {
    private String userName;
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
    }
    public void register(String userName, String email, String password, String bankAccount){
        System.out.println("Enter your username: ");
        userName = scanner.nextLine();
        System.out.println("Enter your email");
        email = scanner.nextLine();
        System.out.println("Enter your password");
        password = scanner.nextLine();
        System.out.println("Enter your bank account");
        bankAccount = scanner.nextLine();
        System.out.println("Account successfully created");

    }

}

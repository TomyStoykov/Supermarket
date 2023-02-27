import ValidateUser.InvalidInputException;
import ValidateUser.ValidateEmail;
import ValidateUser.ValidatePassword;
import ValidateUser.ValidateUserName;

import java.io.*;
import java.util.Scanner;

//check username email for login
public class UserFactory {

    static boolean loggedIn = false;
    public static void register() {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        try {
            System.out.println("Enter your username: ");
            String userName = scanner.nextLine();
            ValidateUserName.isValidUsername(userName);
            System.out.println("Enter your password");
            String password = scanner.nextLine();
            ValidatePassword.isValidPassword(password);
            System.out.println("Enter your email");
            String email = scanner.nextLine();
            ValidateEmail.isValidEmail(email);
            System.out.println("Enter your bank account");
            String bankAccount = scanner.nextLine();
            user = new User(userName, password, email, bankAccount);
        }catch (InvalidInputException e){
            System.out.println(e.getMessage());

        }
        if(user != null)
        try{
            FileWriter writer = new FileWriter(user.getUserName()+ ".txt");
            writer.write(user.getUserName() + "\n");
            writer.write(user.getPassword() + "\n");
            writer.write(user.getEmail() + "\n");
            writer.write(user.getBankAccount() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void logIn() {
        Scanner scanner = new Scanner(System.in);
        String userName;
        String password;
        while (!loggedIn) {
            System.out.println("Enter username or 'exit' if you want to close the log in");
            userName = scanner.nextLine();
            if (userName.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Enter password or 'exit' if you want to close the log in");
            password = scanner.nextLine();
            if(password.equalsIgnoreCase("exit")){
                break;
            }

        File file = new File(userName + ".txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String storedUserName = reader.readLine();
            String storedPassword = reader.readLine();
            String storedEmail = reader.readLine();
            reader.close();

            if(storedUserName.equals(userName)  && storedPassword.equals(password)){
                loggedIn = true;
                System.out.println("You have successfully logged in");
            }
            else{
                System.out.println("Username or password is incorrect");
            }
        } catch (FileNotFoundException e){
            System.out.println("User not found. Check if you have entered the credentials correctly");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    }
}

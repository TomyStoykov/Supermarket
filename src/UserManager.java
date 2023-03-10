import ValidateUser.InvalidInputException;
import ValidateUser.ValidateEmail;
import ValidateUser.ValidatePassword;
import ValidateUser.ValidateUserName;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager {

    static boolean loggedIn = false;


    public static void register() {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        try {
            System.out.println("Enter your username: ");
            String userName = scanner.nextLine();
            ValidateUserName.isValidUsername(userName);
            if(HashMapDB.readUserEmailMapFromFile() != null && HashMapDB.readUserEmailMapFromFile().containsValue(userName)){
                throw new InvalidInputException("Username is already in use");
            }
            System.out.println("Enter your password");
            String password = scanner.nextLine();
            ValidatePassword.isValidPassword(password);
            System.out.println("Enter your email");
            String email = scanner.nextLine();
            ValidateEmail.isValidEmail(email);
            if(HashMapDB.readUserEmailMapFromFile() != null && HashMapDB.readUserEmailMapFromFile().containsKey(email)){
                throw new InvalidInputException("Email is already in use");
            }
            System.out.println("Enter your bank account");
            String bankAccount = scanner.nextLine();
            HashMapDB.userEmailMap = new HashMap<>();
            HashMapDB.userEmailMap.put(email, userName);
            user = new User(userName, password, email, bankAccount);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            System.exit(1);

        }
            try {
                FileWriter writer = new FileWriter(user.getUserName() + ".txt");
                writer.write(user.getUserName() + "\n");
                writer.write(user.getPassword() + "\n");
                writer.write(user.getEmail() + "\n");
                writer.write(user.getBankAccount() + "\n");
                writer.close();
                writer = new FileWriter("UsersHashMap.txt",true);
                HashMapDB.writeUserEmailMapToFile(HashMapDB.userEmailMap);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public static void logIn() {
        if (HashMapDB.userEmailMap == null) {
            HashMapDB.userEmailMap = HashMapDB.readUserEmailMapFromFile();
        }
        Scanner scanner = new Scanner(System.in);
        String userNameOrEmail;
        String password;
        while (!loggedIn) {
            System.out.println("Enter username or email or 'exit' if you want to close the log in");
            userNameOrEmail = scanner.nextLine();
            if (userNameOrEmail.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Enter password or 'exit' if you want to close the log in");
            password = scanner.nextLine();
            if (password.equalsIgnoreCase("exit")) {
                break;
            }

            if (HashMapDB.userEmailMap.containsKey(userNameOrEmail)) {
                String storedUserName = HashMapDB.userEmailMap.get(userNameOrEmail);
                File file = new File(storedUserName + ".txt");
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    reader.readLine();
                    String storedPassword = reader.readLine();
                    reader.close();

                    if (storedPassword.equals(password)) {
                        loggedIn = true;
                        System.out.println("You have successfully logged in");
                    } else {
                        System.out.println("Password is incorrect");
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("User not found. Check if you have entered the credentials correctly");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                File file = new File(userNameOrEmail + ".txt");
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String storedUserName = reader.readLine();
                    String storedPassword = reader.readLine();
                    reader.close();

                    if (storedUserName.equals(userNameOrEmail) && storedPassword.equals(password)) {
                        loggedIn = true;
                        System.out.println("You have successfully logged in");
                    } else {
                        System.out.println("Username or password is incorrect");
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("User not found. Check if you have entered the credentials correctly");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



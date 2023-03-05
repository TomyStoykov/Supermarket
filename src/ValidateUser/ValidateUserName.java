package ValidateUser;

import java.util.regex.Pattern;

public class ValidateUserName {
    public static void isValidUsername(String userName) throws InvalidInputException{
        final Pattern userNamePatter = Pattern.compile("^[a-zA-Z0-9_-]{3,15}");
        if(!userNamePatter.matcher(userName).matches()){
            throw new InvalidInputException("Invalid username input");
        }
    }
}
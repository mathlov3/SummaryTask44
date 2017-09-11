package ua.nure.sliva.SummaryTask4.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public String validate(String login,String password){
        if(login == null || password == null || login.isEmpty() || password.isEmpty()){
            return "Invalid login or password";
        }
        Pattern pattern = Pattern.compile("[A-Za-z0-9_]{3,24}");
        Matcher matcher = pattern.matcher(login);
        if(!matcher.matches()){
            return "Invalid login or password";
        }
        matcher = pattern.matcher(login);
        if(!matcher.matches()){
            return "Invalid login or password";
        }
        return null;
    }
}

package ua.nure.sliva.SummaryTask4.util;

import ua.nure.sliva.SummaryTask4.constants.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public String validate(String login,String password){
        if(login == null || password == null || login.isEmpty() || password.isEmpty()){
            return "Invalid login or password";
        }
        Pattern pattern = Pattern.compile(Validation.LOGIN_PATTERN);
        Matcher matcher = pattern.matcher(login);
        if(!matcher.matches()){
            return "Incorrect login or password";
        }
        pattern = Pattern.compile(Validation.PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        if(!matcher.matches()){
            return "Incorrect login or password";
        }
        return null;
    }
    public String validate(String login,String name,String email,String password,String repassword){
        if(login == null || name == null || email == null || password == null
                || login.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() || repassword.isEmpty()){
            return "Invalid register data";
        }
        Pattern pattern = Pattern.compile(Validation.LOGIN_PATTERN);
        Matcher matcher = pattern.matcher(login);
        if(!matcher.matches()){
            return "Incorrect register data";
        }
        pattern = Pattern.compile(Validation.PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        if(!matcher.matches()){
            return "Incorrect register data";
        }
        pattern = Pattern.compile(Validation.PASSWORD_PATTERN);
        matcher = pattern.matcher(repassword);
        if(!matcher.matches()){
            return "Incorrect register data";
        }
        pattern = Pattern.compile(Validation.NAME_PATTERN);
        matcher = pattern.matcher(name);
        if(!matcher.matches()){
            return "Incorrect register data";
        }
        pattern = Pattern.compile(Validation.EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        if(!matcher.matches()){
            return "Incorrect register data";
        }
        return null;
    }
}

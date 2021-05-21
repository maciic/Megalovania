package hu.unideb.inf.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public boolean validate(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}



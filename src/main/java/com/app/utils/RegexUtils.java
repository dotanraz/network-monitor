package com.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static Matcher find(String text, String pattern) {
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(text);
        if (matcher.find( )) {
            return matcher;
        }else {
            System.out.println("NO MATCH");
            return null;
        }
    }

}

package com.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    static String IP_PATTERN = "\\d+\\.\\d+\\.\\d+\\.\\d+";
    static String MAC_ADDRESS_PATTERN = "([a-zA-Z0-9]*[:-]){5}[a-zA-Z0-9]*";

    public static Matcher find(String text, String pattern) {
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(text);
        if (matcher.find( )) {
            return matcher;
        }else {
//            System.out.println("NO MATCH");
            return null;
        }
    }

    public static boolean isTextContainsIp(String text) {
        Matcher matcher = find(text, IP_PATTERN);
        if (matcher == null) {
            return false;
        } else return true;
    }

    public static boolean isTextContainsMacAddress(String text) {
        Matcher matcher = find(text, MAC_ADDRESS_PATTERN);
        if (matcher == null) {
            return false;
        } else return true;
    }

    public static Matcher extractIpFromText(String text) {
        return find(text, IP_PATTERN);
    }

    public static Matcher extractMacAddressFromText(String text) {
        return find(text, MAC_ADDRESS_PATTERN);
    }


}

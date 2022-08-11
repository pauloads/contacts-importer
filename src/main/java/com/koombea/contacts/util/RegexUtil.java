package com.koombea.contacts.util;

import java.util.regex.Pattern;

public class RegexUtil {

    private static final String ONLY_NUMBERS_REGEX = "^[0-9]*$";

    public static boolean containsOnlyNumbers(final String text){
        return Pattern.matches(ONLY_NUMBERS_REGEX, text);
    }
}

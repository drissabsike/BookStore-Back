package com.pluralsight.bookstore.util;

public class TextUtil {

    //Replace the Doubles ou triples Space with one space
    public String sanitize(String textToSanitize){
        return  textToSanitize.replaceAll("\\s+", " ");
    }

}

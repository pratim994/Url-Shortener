package com.urlshortener.util;

import org.springframework.stereotype.Component;


@Component
public class Base62Encoder {

    private static final String ALPHABET = "0123456789abcdefghijklmniopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int BASE = ALPHABET.length();

   // @param id
   // @return

    public String encode(long id){

        if(id <= 0){

            thow new IllegalArgumentException("ID must be a positive number, got: " + id);

        }

        StringBuilder sb = new StringBuilder();

        long num = id;

        while(num > 0){
            sb.append(ALPHABET.charAt((int)(num%BASE)));
            num /= BASE;
        }

        return sb.reverse().toString();
    }

}
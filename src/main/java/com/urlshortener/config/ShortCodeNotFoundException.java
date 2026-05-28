package com.urlshortener.config;



public class ShortCodeNotFoundException extends RuntimeException {

    public ShortCodeNotFoundException(String code) {
        super("No URL found for short code: " + code);
    }
}

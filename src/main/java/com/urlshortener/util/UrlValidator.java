package com.urlshortener.util;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;



@Component
public class UrlValidator{

    @param url

    public boolean isValid(String url){

        if(url == null || url.isBlank()){
            return false;

        }

        try {
            URI uri = new URI(url);

            String scheme = uri.getScheme();

            return ("http".equalsIgnoreCase(scheme) ||  "https".equalsIgnoreCase(scheme))
                    && uri.getHost() != null
                    && !uri.getHost().isBlank();

        } catch (URISyntaxException e){  return false;}
    }
}




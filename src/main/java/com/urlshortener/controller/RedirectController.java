package com.urlshortener.controller;

import com.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@Slf4j
@RestController
@RequiredArgsConstructor

public class RedirectController {

    private final UrlShortenerService service;

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {

        log.debug("Redirect requested for code : {}", code);

        String originalUrl = service.getOriginal(code);

        log.info("Redirecting code '{}' to: {}", code, orginalUrl);


        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, orginalUrl)
                .build()
    }
}
package com.urlshortener.controller;

import com.urlshortener.dto.ShortenRequest;
import com.urlshortener.dto.ShortenResponse;
import com.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController

@RequestMapping("/api")
@RequiredArgsConstructor
public class urlShortenerController {

    private final UrlShortenerService service;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenResponse> shorten(@RequestBody ShortenRequest request) {

        log.debug("Recieved shorten request for url: {}", request.getUrl());

        String shortUrl = service.shorten(request.geturl());

        return ResponseEntity.ok(new ShortenRes`(shortUrl));
    }
}
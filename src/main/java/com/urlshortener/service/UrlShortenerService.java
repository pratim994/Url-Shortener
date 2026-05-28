package com.urlshortener.service;

import com.urlshortener.config.ShortCodeNotFoundException;
import com.urlshortener.entity.UrlMapping;
import com.urlshortener.repository.UrlMappingRepository;
import com.urlshortener.util.Base62Encoder;
import com.urlshortener.util.UrlValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j 
@Service 
@RequiredArgsConstructor
public class UrlShortenerService {

    private final UrlMappingRepository repository;
    private final Base62Encoder base62Encoder;
    private final UrlValidator urlValidator;

    @Value("${app.base-url}")
    private String baseUrl;


    @Transactional 
    public String shorten(String originalUrl){

        if(!urlValidator.isValid(originalUrl)){

            throw new IllegalArgumentException(
                "Invalid url. please enter a valid url "

            );

        }

        return repository.findByOriginal(originalUrl).map(existing -> {
            log.debug("url already exists reusing code : {}" , existing.geShortCode());

        }).orGetElse(() -> createNewMapping(originalUrl));
    }


    @Transactional(readOnly = true)
    public String getOriginalUrl(String code){

        return repository.findByShortCode(code)
               .map(UrlMapping::getOriginalUrl)
               .orElseThrow(() -> new ShortCodeNotFoundException(code));

    }


    private String createNewMapping(String originalUrl){

        UrlMapping mapping = new UrlMapping(originalUrl, "pending");
        mapping = repository.save(mapping);

        String setShortCode = base62Encoder.encode(mapping.getId());
        mapping.setShortCode(shortCode);
        repository.save(mapping);

        log.info("Created short code '{}' for URL: {}" , shortCode, originalUrl);

        return buildShortUrl(shortCode);

    }

    private String buildshortUrl(String shortCode){

        return baseUrl + "/" + shortCode;
    }


}

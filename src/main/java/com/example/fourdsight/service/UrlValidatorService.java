package com.example.fourdsight.service;

import com.example.fourdsight.exception.InvalidUrlException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
@Service
public class UrlValidatorService {

    public static boolean isValidUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException ex) {
            log.error("Invalid url with exception message :{}", ex.getMessage());
            throw new InvalidUrlException(ex.getMessage());
        } catch (URISyntaxException ex) {
            log.error("Invalid url syntax with exception message : {}", ex.getMessage());
            throw new InvalidUrlException(ex.getMessage());
        }
    }
}

package com.example.fourdsight.service;

import com.example.fourdsight.exception.InvalidUrlException;
import org.junit.Assert;
import org.testng.annotations.Test;

public class UrlValidatorServiceTest {

    @Test
    public void shouldBeValidWhenUrlIsCorrect(){
        String url = "https://www.google.com";
        boolean isValid = UrlValidatorService.isValidUrl(url);
        Assert.assertTrue(isValid);
    }

    @Test(expectedExceptions = InvalidUrlException.class)
    public void shouldNotBeValidWhenUrlIsIncorrect(){
        String url = "httpw.google.com";
        boolean isValid = UrlValidatorService.isValidUrl(url);
        Assert.assertFalse(isValid);
    }
}

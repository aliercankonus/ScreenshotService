package com.example.fourdsight.service;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Service
public class ScreenshotService {

    private WebDriver webDriver;

    public ScreenshotService() {
        System.setProperty(
                "webdriver.firefox.marionette",
                System.getProperty("user.dir") + File.separator + "geckodriver.exe");
        this.webDriver = new FirefoxDriver();
    }

    public byte[] getScreenshotFromUrl(String url) throws IOException {
        webDriver.get(url);
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("./Secreenshots/" + new URL(url).getHost() + ".jpg"));
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }
}

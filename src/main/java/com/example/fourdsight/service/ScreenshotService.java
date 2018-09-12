package com.example.fourdsight.service;

import com.example.fourdsight.model.resource.ThreadSafeFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Slf4j
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
    log.info(
        "Thread name : {}. Screenshot will be taken. Url : {}",
        Thread.currentThread().getName(),
        url);
    ThreadSafeFile file = getFile(url);
    FileUtils.copyFile(
        file.getFile(), new File("./Secreenshots/" + new URL(url).getHost() + ".jpg"));
    log.info("Thread name : {}. Writing to file. Url : {}", Thread.currentThread().getName(), url);
    return FileUtils.readFileToByteArray(file.getFile());
  }

  public synchronized ThreadSafeFile getFile(String url) {
    webDriver.get(url);
    log.info("input url : {}", url);
    log.info("url web driver : {}", webDriver.getCurrentUrl());
    return new ThreadSafeFile(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE));
  }
}

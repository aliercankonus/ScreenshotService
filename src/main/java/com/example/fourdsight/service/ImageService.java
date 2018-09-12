package com.example.fourdsight.service;

import com.example.fourdsight.communication.ImageSender;
import com.example.fourdsight.communication.message.ImageMessage;
import com.example.fourdsight.repository.ImageRepository;
import com.example.fourdsight.model.entity.ImageEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ImageService {

    private final ImageSender imageSender;
    private final ImageRepository imageRepository;
    private final ConversionService conversionService;
    private final ScreenshotService screenshotService;

    private ImageMessage imageMessage;

    @Autowired
    public ImageService(
            ImageSender imageSender,
            ImageRepository imageRepository,
            ConversionService conversionService,
            ScreenshotService screenshotService) {
        this.imageSender = imageSender;
        this.imageRepository = imageRepository;
        this.conversionService = conversionService;
        this.screenshotService = screenshotService;
        this.imageMessage = new ImageMessage();
    }

    public void createImageFromUrl(ImageMessage message) throws IOException {
        ImageEntity entity = conversionService.convert(message, ImageEntity.class);
        byte[] image = screenshotService.getScreenshotFromUrl(message.getUrl());
        entity.setImage(image);
        imageRepository.save(entity);
    }

    public void validateAndSendMessageToQueue(List<String> urlList) {
        urlList.stream().forEach(url -> UrlValidatorService.isValidUrl(url));
        urlList.stream().forEach(url -> sendMessageToQueue(url));
    }

    private void sendMessageToQueue(String url) {
        imageMessage.setUrl(url);
        imageSender.sendMessage(imageMessage);
    }


}

package com.example.fourdsight.communication;

import com.example.fourdsight.communication.message.ImageMessage;
import com.example.fourdsight.config.ImageConfig;
import com.example.fourdsight.service.ImageService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ImageListener {

    private final ImageService imageService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ImageListener(ImageService imageService, ObjectMapper objectMapper) {
        this.imageService = imageService;
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = ImageConfig.QUEUE_NAME)
    public void receiveMessage(final String message) {
        log.info("Message is handled : {}", message);
        try {
            ImageMessage imageMessage = objectMapper.readValue(message, ImageMessage.class);
            imageService.createImageFromUrl(imageMessage);
        } catch (JsonMappingException ex) {
            log.error("Json mapping exception is occured. Exception message : {}", ex.getMessage());
        } catch (JsonParseException ex) {
            log.error("Json parsing exception is occured. Exception message : {}", ex.getMessage());
        } catch (IOException ex) {
            log.error("IOexception is occured. Exception message : {}", ex.getMessage());
        }
    }
}

package com.example.fourdsight.communication;

import com.example.fourdsight.communication.message.ImageMessage;
import com.example.fourdsight.config.ImageConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ImageSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public ImageSender(JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(ImageMessage message) {
        try {
            log.info("Message is arrived : {}", message);
            if (message != null) {
                jmsTemplate.convertAndSend(
                        ImageConfig.QUEUE_NAME, convertImageMessageToString(message));
            } else {
                log.error("Message is not valid!");
            }

        } catch (JmsException | JsonProcessingException ex) {
            log.error(ex.getMessage());
        }
    }

    private String convertImageMessageToString(ImageMessage message)
            throws JsonProcessingException {
        return objectMapper.writeValueAsString(message);
    }
}

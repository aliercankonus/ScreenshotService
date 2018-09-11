package com.example.fourdsight.communication;

import com.example.fourdsight.communication.message.ImageMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.example.fourdsight.util.TestUtil.MESSAGE_URL;
import static com.example.fourdsight.util.TestUtil.createImageMessage;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ImageSenderTest {

    @Mock private JmsTemplate jmsTemplateMock;
    @Mock private ObjectMapper objectMapperMock;
    @InjectMocks private ImageSender imageSender;

    @BeforeMethod
    public void init(){
        MockitoAnnotations.initMocks(this);
        imageSender = new ImageSender(jmsTemplateMock, objectMapperMock);
    }

    @Test
    public void shouldSendMessageToQueue() throws JsonProcessingException {
        ImageMessage message = createImageMessage(MESSAGE_URL);
        String messageText = "Message";
        when(objectMapperMock.writeValueAsString(message)).thenReturn(messageText);
        imageSender.sendMessage(message);
        Mockito.verify(jmsTemplateMock, Mockito.times(1)).convertAndSend(anyString(), eq(messageText));
    }

    @Test
    public void shouldNotSendMessageWhenImageIsNotvalid() throws JsonProcessingException {
        ImageMessage message = null;
        String messageText = "Message";
        imageSender.sendMessage(message);
        Mockito.verify(jmsTemplateMock, Mockito.never()).convertAndSend(anyString(), eq(messageText));
    }
}

package com.example.fourdsight.communication;

import com.example.fourdsight.communication.message.ImageMessage;
import com.example.fourdsight.service.ImageService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.example.fourdsight.util.TestUtil.MESSAGE_URL;
import static com.example.fourdsight.util.TestUtil.createImageMessage;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageListenerTest {

    @Mock private ImageService imageServiceMock;
    @Mock private ObjectMapper objectMapperMock;

    @InjectMocks private ImageListener imageListener;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        imageListener =  new ImageListener(imageServiceMock, objectMapperMock);
    }

    @Test
    public void shouldCreateImageWhenMessageIsValid() throws IOException {
        ImageMessage message = createImageMessage(MESSAGE_URL);
        String messageText = "Message";
        when(objectMapperMock.readValue(messageText, ImageMessage.class)).thenReturn(message);
        imageListener.receiveMessage(messageText);
        verify(imageServiceMock, times(1)).createImageFromUrl(eq(message));
    }

    @Test
    public void shouldCreateImageWhenMessageIsInvalid() throws IOException {
        ImageMessage message = createImageMessage(MESSAGE_URL);
        String messageText = "Message";
        when(objectMapperMock.readValue(messageText, ImageMessage.class)).thenThrow(JsonParseException.class);
        imageListener.receiveMessage(messageText);
        verify(imageServiceMock, times(0)).createImageFromUrl(eq(message));
    }
}

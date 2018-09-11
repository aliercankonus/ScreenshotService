package com.example.fourdsight.converter;

import com.example.fourdsight.communication.message.ImageMessage;
import com.example.fourdsight.model.entity.ImageEntity;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.example.fourdsight.util.TestUtil.MESSAGE_URL;
import static com.example.fourdsight.util.TestUtil.createImageMessage;

public class ImageMessageToImageEntityConverterTest {

    @InjectMocks private ImageMessageToImageEntityConverter converterTest;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldConvertImageMessageToEntity(){
        ImageMessage message = createImageMessage(MESSAGE_URL);
        ImageEntity entity = converterTest.convert(message);
        Assert.assertNotNull(entity.getUrl());
        Assert.assertEquals(entity.getUrl(), message.getUrl());
    }

    @Test
    public void shouldNotConvertImageMessageToEntityWhenMessageIsNull(){
        ImageMessage message = null;
        ImageEntity entity = converterTest.convert(message);
        Assert.assertNull(entity.getUrl());
    }
}

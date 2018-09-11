package com.example.fourdsight.service;

import com.example.fourdsight.communication.ImageSender;
import com.example.fourdsight.communication.message.ImageMessage;
import com.example.fourdsight.exception.InvalidUrlException;
import com.example.fourdsight.model.entity.ImageEntity;
import com.example.fourdsight.repository.ImageRepository;
import com.example.fourdsight.util.TestUtil;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static com.example.fourdsight.util.TestUtil.MESSAGE_URL;
import static com.example.fourdsight.util.TestUtil.createImageMessage;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageServiceTest {

    @Mock private ImageSender imageSenderMock;
    @Mock private ConversionService conversionServiceMock;
    @Mock private ImageRepository imageRepositoryMock;
    @Mock private ScreenshotService screenshotServiceMock;
    @InjectMocks private ImageService imageService;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        imageService =
                new ImageService(
                        imageSenderMock,
                        imageRepositoryMock,
                        conversionServiceMock,
                        screenshotServiceMock);
    }

    @Test
    public void shouldSaveImage() throws IOException {
        ImageMessage message = createImageMessage(MESSAGE_URL);
        ImageEntity entity = TestUtil.createImageEntity(MESSAGE_URL);
        byte[] byteImage = MESSAGE_URL.getBytes();
        when(conversionServiceMock.convert(message, ImageEntity.class)).thenReturn(entity);
        when(screenshotServiceMock.getScreenshotFromUrl(MESSAGE_URL)).thenReturn(byteImage);
        imageService.createImageFromUrl(message);
        verify(imageRepositoryMock, times(1)).save(entity);
        Assert.assertEquals(entity.getImage(), byteImage);
    }

    @Test
    public void shouldSendMessageWhenUrlListIsValid(){
        List<String> urlList = TestUtil.createUrlList();
        imageService.validateAndSendMessageToQueue(urlList);
        Mockito.verify(imageSenderMock, times(urlList.size())).sendMessage(any(ImageMessage.class));
    }

    @Test(expectedExceptions = InvalidUrlException.class)
    public void shouldThrowExceptionWhenUrlListIsInvalid(){
        List<String> urlList = TestUtil.createUrlList();
        urlList.add("invalidUrl");
        imageService.validateAndSendMessageToQueue(urlList);
    }
}

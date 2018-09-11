package com.example.fourdsight.converter;

import com.example.fourdsight.communication.message.ImageMessage;
import com.example.fourdsight.model.entity.ImageEntity;
import org.springframework.core.convert.converter.Converter;

public class ImageMessageToImageEntityConverter implements Converter<ImageMessage, ImageEntity> {
    @Override
    public ImageEntity convert(ImageMessage imageMessage) {
        ImageEntity imageEntity =  new ImageEntity();
        if(imageMessage !=null){
            imageEntity.setUrl(imageMessage.getUrl());
        }
        return imageEntity;
    }
}

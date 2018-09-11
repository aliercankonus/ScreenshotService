package com.example.fourdsight.util;

import com.example.fourdsight.communication.message.ImageMessage;
import com.example.fourdsight.model.entity.ImageEntity;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static final String MESSAGE_URL = "https://www.google.com";


    public static ImageMessage createImageMessage(String url){
        ImageMessage message = new ImageMessage();
        message.setUrl(url);
        return message;
    }

    public static ImageEntity createImageEntity(String url){
        ImageEntity entity = new ImageEntity();
        entity.setUrl(url);
        return entity;
    }

    public static List<String> createUrlList(){
        List<String> list = new ArrayList<>();
        list.add("https://www.google.com");
        list.add("https://www.facebook.com");
        return list;

    }
}

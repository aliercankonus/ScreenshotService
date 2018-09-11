package com.example.fourdsight.model.entity;

import com.example.fourdsight.util.Constants;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity(name = "image_url")
@Table(indexes = {@Index(name = "idx_image_url", columnList = "url")})
public class ImageEntity {
    @Id
    @Column(name = "url", length = Constants.URL_MAX_LENGTH)
    private String url;

    @Column
    private byte[] image;
}

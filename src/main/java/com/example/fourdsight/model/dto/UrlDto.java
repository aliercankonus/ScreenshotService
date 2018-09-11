package com.example.fourdsight.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UrlDto {
    @NotEmpty
    private List<String> urlList;
}

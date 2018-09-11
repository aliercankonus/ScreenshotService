package com.example.fourdsight.config;

import com.example.fourdsight.converter.ImageMessageToImageEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.support.ConfigurableConversionService;

import javax.annotation.PostConstruct;

/** Registering converters. */
@Configuration
public class ConverterConfig {

    @Autowired
    @Qualifier("mvcConversionService")
    private ConfigurableConversionService conversionService;

    @PostConstruct
    public void postConstruct() {
        conversionService.addConverter(new ImageMessageToImageEntityConverter());
    }

    @Bean
    @Primary
    public ConfigurableConversionService getConversionService() {
        return conversionService;
    }
}

package com.example.compositeservice.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

import feign.codec.Encoder;
import feign.form.FormEncoder;

public class FeignEncoderConfig {
	
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;
    @Bean
    public Encoder encoder() {
        return new FormEncoder();
    }
}

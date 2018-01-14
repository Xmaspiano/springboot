package com.springboot.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

public class WebConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(fastJsonHttpMessageConverter());
//    }

    @Bean
    public HttpMessageConverter fastJsonHttpMessageConverter(){
        return new com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter();
    }
}

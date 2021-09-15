package com.c332030.util.comm;

import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import org.springframework.web.client.RestTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

@Configuration
public class CommConfiguration {

    @Bean
    public static OkHttpClient okHttpClient() {
        return OkHttpUtils.OK_HTTP_CLIENT;
    }

    @Bean
    public static ConnectionPool connectionPool() {
        return OkHttpUtils.CONNECTION_POOL;
    }

    @Bean
    public static RestTemplate restTemplate() {
        return RestTemplateUtils.REST_TEMPLATE;
    }

    @Bean
    public static OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
        return RestTemplateUtils.OK_HTTP_3_CLIENT_HTTP_REQUEST_FACTORY;
    }

    @Bean
    public static GsonHttpMessageConverter gsonHttpMessageConverter() {
        return RestTemplateUtils.GSON_HTTP_MESSAGE_CONVERTER;
    }

}

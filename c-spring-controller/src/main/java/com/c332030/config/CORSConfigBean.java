package com.c332030.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import com.c332030.util.data.StringUtils;

/**
 * <p>
 * Description: CORSConfigFactory
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
@Component
@PropertySource(
    value = "classpath:cors.properties"
    , ignoreResourceNotFound = true
)
public class CORSConfigBean {

    /**
     * origin 请求源
     */
    @Value("${origins:}")
    private String origins;

    /**
     * origin 请求方式
     */
    @Value("${methods:}")
    private String methods;

    /**
     * origin 请求头
     */
    @Value("${headers:}")
    private String headers;

    /**
     * origin cookies 支持
     */
    @Value("${credentials:false}")
    private boolean credentials;

    @Bean
    public CORSConfig getCORSConfig() {

        log.info("origins: {}, methods: {}, headers: {}, credentials: {}", origins, methods, headers, credentials);

        boolean originsEmpty = StringUtils.isEmpty(origins);
        boolean methodsEmpty = StringUtils.isEmpty(methods);
        boolean headersEmpty = StringUtils.isEmpty(headers);
        if(originsEmpty && methodsEmpty && headersEmpty) {
            log.warn("cors attribute are empty");
            return CORSConfig.EMPTY;
        }

        CORSConfig corsConfig = new CORSConfig();
        corsConfig.setOrigins(Arrays.asList(origins.split(",")));
        corsConfig.setMethods(methodsEmpty ? null : methods);
        corsConfig.setHeaders(headersEmpty ? null : headers);
        corsConfig.setCredentials(credentials);
        return corsConfig;
    }
}

package com.c332030.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

import com.c332030.util.data.GsonUtils;

@Configuration
public class UtilsConfiguration {

    @Bean
    public static Gson gson() {
        return GsonUtils.GSON;
    }

}

package com.c332030.controller.advice;


import javax.annotation.Nonnull;

import org.springframework.core.MethodParameter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import lombok.extern.slf4j.Slf4j;

import com.c332030.web.model.comm.BaseComm;

/**
 * <p>
 * Description: ResponseBodyAdvice
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
// @ControllerAdvice
public class CResponseBodyAdvice implements org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice<BaseComm> {

    @Override
    public boolean supports(
        @Nonnull MethodParameter returnType,
        @Nonnull Class<? extends HttpMessageConverter<?>> converterType
    ) {

        log.debug("returnType: {}, converterType: {}", returnType, converterType);

        return true;
    }

    @Override
    public BaseComm beforeBodyWrite(
        BaseComm body,
        @Nonnull MethodParameter returnType,
        @Nonnull MediaType selectedContentType,
        @Nonnull Class<? extends HttpMessageConverter<?>> selectedConverterType,
        @Nonnull ServerHttpRequest request,
        @Nonnull ServerHttpResponse response
    ) {

        log.debug("body: {}", body);

        return body;
    }
}

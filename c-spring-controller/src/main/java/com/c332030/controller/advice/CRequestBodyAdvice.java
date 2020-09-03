package com.c332030.controller.advice;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.annotation.Nonnull;

import org.springframework.core.MethodParameter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;

import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description: RequestBodyAdvice
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
// @ControllerAdvice
public class CRequestBodyAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(
        @Nonnull MethodParameter methodParameter,
        @Nonnull Type targetType,
        @Nonnull Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return true;
    }

    @Override
    public @Nonnull HttpInputMessage beforeBodyRead(
        @Nonnull HttpInputMessage inputMessage,
        @Nonnull MethodParameter parameter,
        @Nonnull Type targetType,
        @Nonnull Class<? extends HttpMessageConverter<?>> converterType
    ) throws IOException {

        log.debug("inputMessage: {}", inputMessage);

        return inputMessage;
    }

    @Override
    public @Nonnull Object afterBodyRead(
        @Nonnull Object body,
        @Nonnull HttpInputMessage inputMessage,
        @Nonnull MethodParameter parameter,
        @Nonnull Type targetType,
        @Nonnull Class<? extends HttpMessageConverter<?>> converterType
    ) {

        log.debug("body: {}, inputMessage: {}", body, inputMessage);

        return body;
    }

    @Override
    public Object handleEmptyBody(
        Object body,
        @Nonnull HttpInputMessage inputMessage,
        @Nonnull MethodParameter parameter,
        @Nonnull Type targetType,
        @Nonnull Class<? extends HttpMessageConverter<?>> converterType
    ) {

        log.debug("body: {}, inputMessage: {}", body, inputMessage);

        return body;
    }
}

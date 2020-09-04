package com.c332030.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

import com.c332030.constant.CharsetConstants;
import com.c332030.util.data.StringUtils;

/**
 * <p>
 * Description: BaseController
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    protected static final String DEFAULT_CONTEXT_TYPE = StringUtils.join(
        new String[] {
            MediaType.TEXT_HTML_VALUE, CharsetConstants.CHARSET_UTF8
        }, ';'
    );

    protected void print(String string) {

        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.print(string);

            response.setContentType(DEFAULT_CONTEXT_TYPE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            log.error("获取响应 Writer 失败", e);
        }

    }
}

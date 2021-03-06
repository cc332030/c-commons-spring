package com.c332030.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

import com.c332030.constant.CharsetConstants;

/**
 * <p>
 * Description: CAbstractController
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public abstract class CAbstractController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    protected static final String DEFAULT_CONTEXT_TYPE = StringUtils.join(
        new String[] {
            MediaType.TEXT_HTML_VALUE, CharsetConstants.CHARSET_UTF8
        }, ';'
    );

    /**
     * <p>
     * Description: 输出字符串
     * </p>
     *
     * @param string 字符串
     * @author c332030
     */
    protected void print(String string) {

        response.setContentType(DEFAULT_CONTEXT_TYPE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.print(string);
        } catch (IOException e) {
            log.error("获取响应 Writer 失败", e);
        }
    }

}

package com.c332030.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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

    /**
     * <p>
     * Description: 获取成功的 newResponseEntity
     * </p>
     *
     * @param t 内容
     * @param <T> 内容类型
     * @return ResponseEntity 实体
     * @author c332030
     */
    protected static <T> ResponseEntity<T> newResponseEntityOK(T t) {
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

}

package com.c332030.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Charsets;

import lombok.extern.slf4j.Slf4j;

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

    protected void print(String string) {

        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.print(string);

            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding(Charsets.UTF_8.name());
        } catch (IOException e) {
            log.error("获取响应 Writer 失败", e);
        }

    }
}

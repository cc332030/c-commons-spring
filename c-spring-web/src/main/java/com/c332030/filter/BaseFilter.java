package com.c332030.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Description: BaseFilter 基础过滤器
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class BaseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    /**
     * <p>
     * Description: 调整参数类型
     * </p>
     *
     * @param request request
     * @param response response
     * @param chain 调用链
     * @throws IOException 业务异常
     * @throws ServletException 业务异常
     * @author c332030
     */
    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;
}

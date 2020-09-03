package com.c332030.util.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * <p>
 * Description: ServletUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class ServletUtils extends com.c332030.web.servlet.util.ServletUtils {

    /**
     * 获取 ServletRequestAttributes
     * @return ServletRequestAttributes
     */
    public static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取 request
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

}

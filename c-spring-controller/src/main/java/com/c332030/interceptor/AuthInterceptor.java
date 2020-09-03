package com.c332030.interceptor;

import javax.annotation.Nonnull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Description: AuthInterceptor 权限拦截器
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public class AuthInterceptor extends BaseInterceptor {

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }
}

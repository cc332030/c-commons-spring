package com.c332030.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;

import org.springframework.core.annotation.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import com.c332030.config.CORSConfig;

/**
 * <p>
 * Description: CORSfilter
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
@Component
@Order(1)
@WebFilter(filterName = "CORSFilter", urlPatterns = "*")
public class CORSFilter extends BaseFilter {

    /**
     * 是否启用过滤器
     */
    private final boolean enable;

    /**
     * 是否有 origin
     */
    private final boolean hasOrigin;

    /**
     * 跨域配置
     */
    private final CORSConfig corsConfig;

    @Autowired
    public CORSFilter(CORSConfig corsConfig) {

        this.corsConfig = corsConfig;
        if(corsConfig.isEmpty()) {
            log.warn("corsConfig is empty");
            this.enable = false;
            this.hasOrigin = false;
            return;
        }
        this.enable = true;

        Collection<String> origins = corsConfig.getOrigins();
        if(CollectionUtils.isEmpty(origins)) {
            log.warn("corsConfig has no origins");
            this.hasOrigin = false;
            return;
        }
        this.hasOrigin = true;
    }

    private static final String TRUE = Boolean.TRUE.toString();

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(enable && hasOrigin) {
            dealResponse(request, response);
        }

        chain.doFilter(request, response);
    }

    private void dealResponse(HttpServletRequest request, HttpServletResponse response) {

        log.debug("corsConfig: {}", corsConfig);

        List<String> origins = corsConfig.getOrigins();

        // origin
        final String origin = request.getHeader("Origin");
        if(origins.contains(origin)) {
            log.error("Not allow origin: {}, origins: {}", origin, origins);
            return;
        }

        response.setHeader("Access-Control-Allow-Origin", origin);

        // methods
        String methods = corsConfig.getMethods();
        if(null != methods) {
            response.setHeader("Access-Control-Allow-Methods", methods);
        }

        // headers
        String headers = corsConfig.getHeaders();
        if(null != headers) {
            response.setHeader("Access-Control-Allow-Headers", headers);
        }

        // credentials
        Boolean credentials = corsConfig.getCredentials();
        if(BooleanUtils.isTrue(credentials)) {
            response.setHeader("Access-Control-Allow-Credentials", TRUE);
        }
    }
}

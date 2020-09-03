package com.c332030.config;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import lombok.Data;

import com.c332030.model.sys.IEmpty;
import com.c332030.util.data.StringUtils;

/**
 * <p>
 * Description: CORSConfig
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Data
// @ConfigurationProperties(prefix = "filter.cors")
public class CORSConfig implements IEmpty {

    /**
     * 空配置
     */
    public static final CORSConfig EMPTY = new CORSConfig() {
        @Override
        public void setOrigins(List<String> origins) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setMethods(String methods) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setHeaders(String headers) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setCredentials(Boolean credentials) {
            throw new UnsupportedOperationException();
        }
    };

    /**
     * origin 请求源
     */
    private List<String> origins;

    /**
     * origin 请求方式
     */
    private String methods;

    /**
     * origin 请求头
     */
    private String headers;

    /**
     * origin cookies 支持
     */
    private Boolean credentials;

    @Override
    public boolean isEmpty() {
        return !isNotEmpty();
    }

    @Override
    public boolean isNotEmpty() {
        return CollectionUtils.isNotEmpty(origins)
            && StringUtils.isNotEmpty(methods)
            && StringUtils.isNotEmpty(headers)
            && null != credentials;
    }
}

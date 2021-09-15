package com.c332030.util.comm;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import org.springframework.core.ParameterizedTypeReference;

import org.springframework.util.MultiValueMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import org.springframework.web.client.RestTemplate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.c332030.util.collection.CSpringMapUtils;
import com.c332030.util.data.GsonUtils;
import com.c332030.util.data.JsonUtils;

/**
 * <p>
 * Description: RestTemplateUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestTemplateUtils {

    public static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public static final OkHttp3ClientHttpRequestFactory OK_HTTP_3_CLIENT_HTTP_REQUEST_FACTORY =
        new OkHttp3ClientHttpRequestFactory(OkHttpUtils.OK_HTTP_CLIENT);

    public static final GsonHttpMessageConverter GSON_HTTP_MESSAGE_CONVERTER =
        new GsonHttpMessageConverter(GsonUtils.GSON);

    static {
        REST_TEMPLATE.setRequestFactory(OK_HTTP_3_CLIENT_HTTP_REQUEST_FACTORY);
        REST_TEMPLATE.getMessageConverters().add(GSON_HTTP_MESSAGE_CONVERTER);
    }

    public static final HttpHeaders POST_FORM_HTTP_HEADERS;
    public static final HttpHeaders POST_JSON_HTTP_HEADERS;
    static {
        POST_FORM_HTTP_HEADERS = getFormHttpHeaders();
        POST_JSON_HTTP_HEADERS = getJsonHttpHeaders();
    }

    public static HttpHeaders initFormHttpHeaders(HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    /**
     * <p>
     * Description: get http headers for post form request
     * </p>
     *
     * @return HttpHeaders for post form
     * @author c332030
     */
    public static HttpHeaders getFormHttpHeaders() {
        return initFormHttpHeaders(new HttpHeaders());
    }

    public static final String CONTENT_TYPE_JSON_WITH_UTF8 = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8";

    /**
     * <p>
     * Description: init HttpHeaders for Json
     * </p>
     *
     * @param headers HttpHeaders Object
     * @return org.springframework.http.HttpHeaders
     * @author c332030
     */
    public static HttpHeaders initJsonHttpHeaders(HttpHeaders headers) {
        headers.set(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON_WITH_UTF8);
        return headers;
    }

    /**
     * <p>
     * Description: get HttpHeaders for Json
     * </p>
     *
     * @return org.springframework.http.HttpHeaders
     * @author c332030
     */
    public static HttpHeaders getJsonHttpHeaders() {
        return initJsonHttpHeaders(new HttpHeaders());
    }

    /**
     * <p>
     * Description: default HttpHeaders
     * </p>
     *
     * @param httpHeaders HttpHeaders
     * @return org.springframework.http.HttpHeaders
     * @author c332030
     */
    public static HttpHeaders dealHttpHeaders(HttpHeaders httpHeaders) {
        return ObjectUtils.defaultIfNull(httpHeaders, HttpHeaders.EMPTY);
    }

    /**
     * <p>
     * Description: get http headers for post json request
     * </p>
     *
     * @param url url
     * @param requestBody requestBody
     * @param tClass result Class
     * @param httpHeaders httpHeaders
     * @param <T> response entity
     * @return http result
     * @author c332030
     */
    public static <T> T post(String url, Object requestBody, Class<T> tClass, HttpHeaders httpHeaders) {
        return REST_TEMPLATE.postForEntity(url,
            new HttpEntity<>(requestBody, dealHttpHeaders(httpHeaders)), tClass
        ).getBody();
    }

    /**
     * <p>
     * Description: post request for form
     * </p>
     *
     * @param url url
     * @param requestBody request requestBody
     * @param tClass response entity class
     * @param <T> response entity
     * @return response requestBody
     * @author c332030
     */
    public static <T> T post(String url, Object requestBody, Class<T> tClass) {
        return post(url, requestBody, tClass, null);
    }

    /**
     * <p>
     * Description: request exchange
     * </p>
     *
     * @param url url
     * @param method request method
     * @param requestBody requestBody
     * @param typeReference response type
     * @param httpHeaders httpHeaders
     * @param <T> response entity
     * @return response requestBody
     * @author c332030
     */
    public static <T> T exchange(String url, HttpMethod method, Object requestBody,
                                 ParameterizedTypeReference<T> typeReference, HttpHeaders httpHeaders) {
        return REST_TEMPLATE.exchange(url, method,
            new HttpEntity<>(requestBody, dealHttpHeaders(httpHeaders)), typeReference
        ).getBody();
    }

    /**
     * <p>
     * Description: request exchange
     * </p>
     *
     * @param url url
     * @param method request method
     * @param requestBody requestBody
     * @param typeReference response type
     * @param <T> response entity
     * @return response requestBody
     * @author c332030
     */
    public static <T> T exchange(String url, HttpMethod method, Object requestBody,
                                     ParameterizedTypeReference<T> typeReference) {
        return exchange(url, method, requestBody, typeReference, null);
    }

    /**
     * <p>
     * Description: request exchange
     * </p>
     *
     * @param url url
     * @param requestBody requestBody
     * @param typeReference response type
     * @param httpHeaders httpHeaders
     * @param <T> response entity
     * @return response requestBody
     * @author c332030
     */
    public static <T> T postExchange(String url, Object requestBody,
                                     ParameterizedTypeReference<T> typeReference,
                                     HttpHeaders httpHeaders) {
        return exchange(url, HttpMethod.POST, requestBody, typeReference, httpHeaders);
    }

    /**
     * <p>
     * Description: post request for form
     * </p>
     *
     * @param url url
     * @param requestBody request requestBody
     * @param tClass response entity class
     * @param httpHeaders http headers
     * @param <T> response entity
     * @return response requestBody
     * @author c332030
     */
    public static <T> T postForForm(String url, MultiValueMap<String, Object> requestBody,
                                    Class<T> tClass, HttpHeaders httpHeaders) {

        if(null == httpHeaders) {
            httpHeaders = POST_FORM_HTTP_HEADERS;
        } else {
            initFormHttpHeaders(httpHeaders);
        }
        return post(url, requestBody, tClass, httpHeaders);
    }

    /**
     * <p>
     * Description: post request for form
     * </p>
     *
     * @param url url
     * @param requestBody request requestBody
     * @param tClass response entity class
     * @param httpHeaders http headers
     * @param <T> response entity
     * @return response requestBody
     * @author c332030
     */
    public static <T> T postForForm(String url, MultiValueMap<String, Object> requestBody,
                                    Class<T> tClass, Map<String, String> httpHeaders) {
        return postForForm(url, requestBody, tClass, CSpringMapUtils.toHttpHeaders(httpHeaders));
    }

    /**
     * <p>
     * Description: post request for form
     * </p>
     *
     * @param url url
     * @param requestBody request requestBody
     * @param tClass response entity class
     * @param httpHeaders http headers
     * @param <T> response entity
     * @return response requestBody
     * @author c332030
     */
    public static <T> T postForForm(String url, Map<String, Object> requestBody,
                                    Class<T> tClass, HttpHeaders httpHeaders) {
        return postForForm(url, CSpringMapUtils.toMultiValueMap(requestBody), tClass, httpHeaders);
    }

    /**
     * <p>
     * Description: post request for form
     * </p>
     *
     * @param url url
     * @param requestBody request requestBody
     * @param tClass response entity class
     * @param httpHeaders http headers
     * @param <T> response entity
     * @return response requestBody
     * @author c332030
     */
    public static <T> T postForForm(String url, Map<String, Object> requestBody,
                                    Class<T> tClass, Map<String, String> httpHeaders) {
        return postForForm(url, CSpringMapUtils.toMultiValueMap(requestBody), tClass, CSpringMapUtils.toHttpHeaders(httpHeaders));
    }

    /**
     * <p>
     * Description: 使用 post 方式发送 form（表单）请求，即模拟网页提交
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param tClass 返回类型
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForForm(String url, MultiValueMap<String, Object> requestBody, Class<T> tClass) {
        return postForForm(url, requestBody, tClass, (HttpHeaders) null);
    }

    /**
     * <p>
     * Description: 使用 post 方式发送 form（表单）请求，即模拟网页提交
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param tClass 返回类型
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForForm(String url, Map<String, Object> requestBody, Class<T> tClass) {
        return postForForm(url, CSpringMapUtils.toMultiValueMap(requestBody), tClass, (HttpHeaders)null);
    }

    /**
     * <p>
     * Description: 使用 post 方式发送 form（表单）请求，即模拟网页提交
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param typeReference 返回类型
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForForm(String url, Object requestBody,
                                    ParameterizedTypeReference<T> typeReference) {
        return postExchange(url, requestBody, typeReference, POST_FORM_HTTP_HEADERS);
    }

    /**
     * <p>
     * Description: 使用 post 方式发送 form（表单）请求，即模拟网页提交
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForForm(String url, MultiValueMap<String, Object> requestBody) {
        return postForForm(url, requestBody, (Class<? extends T>) null);
    }

    /**
     * <p>
     * Description: 使用 post 方式发送 form（表单）请求，即模拟网页提交
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForForm(String url, Map<String, Object> requestBody) {
        return postForForm(url, CSpringMapUtils.toMultiValueMap(requestBody), (Class<? extends T>) null);
    }

    /**
     * <p>
     * Description: 使用 post 方式发送 form（表单）请求，即模拟网页提交
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param typeReference response type
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForForm(String url, MultiValueMap<String, Object> requestBody,
                                    ParameterizedTypeReference<T> typeReference) {
        return postExchange(url, requestBody, typeReference, POST_JSON_HTTP_HEADERS);
    }

    /**
     * <p>
     * Description: 使用 post 方式发送 form（表单）请求，即模拟网页提交
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param typeReference response type
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForForm(String url, Map<String, Object> requestBody,
                                    ParameterizedTypeReference<T> typeReference) {
        return postForForm(url, CSpringMapUtils.toMultiValueMap(requestBody), typeReference);
    }

    /**
     * <p>
     * Description: 使用 json 方式发送请求
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param tClass 返回类型
     * @param httpHeaders 请求头
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForJson(String url, String requestBody, Class<T> tClass, HttpHeaders httpHeaders) {

        if(null == httpHeaders) {
            httpHeaders = POST_JSON_HTTP_HEADERS;
        } else {
            initJsonHttpHeaders(httpHeaders);
        }
        return post(url, requestBody, tClass, httpHeaders);
    }

    /**
     * <p>
     * Description: 使用 json 方式发送请求
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param tClass 返回类型
     * @param httpHeaders 请求头
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForJson(String url, String requestBody, Class<T> tClass, Map<String, String> httpHeaders) {
        return postForJson(url, requestBody, tClass, CSpringMapUtils.toHttpHeaders(httpHeaders));
    }

    /**
     * <p>
     * Description: 使用 json 方式发送请求
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param tClass 返回类型
     * @param httpHeaders 请求头
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForJson(String url, Object requestBody, Class<T> tClass, HttpHeaders httpHeaders) {
        return postForJson(url, JsonUtils.toJson(requestBody), tClass, httpHeaders);
    }

    /**
     * <p>
     * Description: 使用 json 方式发送请求
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param tClass 返回类型
     * @param httpHeaders 请求头
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForJson(String url, Object requestBody, Class<T> tClass, Map<String, String> httpHeaders) {
        return postForJson(url, JsonUtils.toJson(requestBody), tClass, CSpringMapUtils.toHttpHeaders(httpHeaders));
    }

    /**
     * <p>
     * Description: 使用 json 方式发送请求
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param tClass 返回类型
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForJson(String url, String requestBody, Class<T> tClass) {
        return postForJson(url, requestBody, tClass, POST_JSON_HTTP_HEADERS);
    }

    /**
     * <p>
     * Description: 使用 json 方式发送请求
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param typeReference 返回类型
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForJson(String url, String requestBody,
                                    ParameterizedTypeReference<T> typeReference) {
        return postExchange(url, requestBody, typeReference, POST_JSON_HTTP_HEADERS);
    }

    /**
     * <p>
     * Description: 使用 json 方式发送请求
     * </p>
     *
     * @param url 链接
     * @param requestBody 请求数据
     * @param typeReference 返回类型
     * @param <T> response entity
     * @return T
     * @author c332030
     */
    public static <T> T postForJson(String url, Object requestBody,
                                    ParameterizedTypeReference<T> typeReference) {
        return postForJson(url, JsonUtils.toJson(requestBody), typeReference);
    }

    /**
     * <p>
     * Description: 使用 json 方式发送请求
     * </p>
     *
     * @param url url
     * @param requestBody json request requestBody
     * @param tClass response entity class
     * @param <T> response entity
     * @return response requestBody
     * @author c332030
     */
    public static <T> T postForJson(String url, Object requestBody, Class<T> tClass) {
        return postForJson(url, JsonUtils.toJson(requestBody), tClass, POST_JSON_HTTP_HEADERS);
    }
}

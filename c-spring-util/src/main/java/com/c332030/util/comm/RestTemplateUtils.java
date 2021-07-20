package com.c332030.util.comm;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * <p>
 * Description: RestTemplateUtils
 * </p>
 *
 * @author c332030（袁兴旺）
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestTemplateUtils {

    public static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private static final HttpHeaders DEFAULT_POST_FORM_HTTP_HEADERS;
    private static final HttpHeaders DEFAULT_POST_JSON_HTTP_HEADERS;
    static {
        DEFAULT_POST_FORM_HTTP_HEADERS = getFormHttpHeaders();
        DEFAULT_POST_JSON_HTTP_HEADERS = getJsonHttpHeaders();
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

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_FORM_URLENCODED);
        return headers;
    }

    /**
     * application json with utf-8
     */
    public static final String CONTENT_TYPE_JSON_WITH_UTF8 = APPLICATION_JSON_VALUE + ";charset=utf-8";

    /**
     * <p>
     * Description: get http headers for post json request
     * </p>
     *
     * @return HttpHeaders for post json
     * @author c332030
     */
    public static HttpHeaders getJsonHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, CONTENT_TYPE_JSON_WITH_UTF8);
        return headers;
    }

    /**
     * <p>
     * Description: post request for form
     * </p>
     *
     * @param url url
     * @param requestBody request body
     * @param tClass response entity class
     * @param <T> response entity
     * @return response body
     * @author c332030
     */
    public static <T> T postForForm(String url, MultiValueMap<String, Object> requestBody, Class<T> tClass) {
        return postForForm(url, requestBody, tClass, DEFAULT_POST_FORM_HTTP_HEADERS);
    }

    /**
     * <p>
     * Description: post request for form
     * </p>
     *
     * @param url url
     * @param requestBody request body
     * @param tClass response entity class
     * @param httpHeaders http headers
     * @param <T> response entity
     * @return response body
     * @author c332030
     */
    public static <T> T postForForm(String url, MultiValueMap<String, Object> requestBody,
                                    Class<T> tClass, HttpHeaders httpHeaders) {

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(requestBody, httpHeaders);
        ResponseEntity<T> responseEntity  = REST_TEMPLATE.postForEntity(url, request, tClass);
        return responseEntity.getBody();
    }

    /**
     * <p>
     * Description: post request for json
     * </p>
     *
     * @param url url
     * @param jsonContent json request body
     * @param tClass response entity class
     * @param <T> response entity
     * @return response body
     * @author c332030
     */
    public static <T> T postForJson(String url, String jsonContent, Class<T> tClass) {
        return postForJson(url, jsonContent, tClass, DEFAULT_POST_JSON_HTTP_HEADERS);
    }

    /**
     * <p>
     * Description: post request for json
     * </p>
     *
     * @param url url
     * @param jsonContent json request body
     * @param tClass response entity class
     * @param httpHeaders http headers
     * @param <T> response entity
     * @return response body
     * @author c332030
     */
    public static <T> T postForJson(String url, String jsonContent, Class<T> tClass, HttpHeaders httpHeaders) {

        HttpEntity<String> request = new HttpEntity<>(jsonContent, httpHeaders);
        ResponseEntity<T> responseEntity  = REST_TEMPLATE.postForEntity(url, request, tClass);
        return responseEntity.getBody();
    }
}

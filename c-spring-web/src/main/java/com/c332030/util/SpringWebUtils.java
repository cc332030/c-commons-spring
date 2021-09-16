package com.c332030.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: SpringWebUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringWebUtils {

    public static final ResponseEntity<String> RESPONSE_ENTITY_EMPTY = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

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
    public static <T> ResponseEntity<T> newResponseEntityOK(T t) {
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

}

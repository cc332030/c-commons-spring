package com.c332030.util;

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

    public static final ResponseEntity<?> RESPONSE_ENTITY_EMPTY = ResponseEntity.noContent().build();

}

package com.c332030.util.collection;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import org.springframework.http.HttpHeaders;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>Description: CSpringMapUtils </p>
 * TABLE:
 *
 * @author c332030
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CSpringMapUtils {

    public static <K, V> MultiValueMap<K, V> toMultiValueMap(Map<K, V> map, boolean singleValue) {

        var multiValueMap = new LinkedMultiValueMap<K, V>(map.size());
        map.forEach((k, v) -> {

            var vList = singleValue
                ? new ArrayList<V>(1)
                : new ArrayList<V>()
                ;
            vList.add(v);
            multiValueMap.put(k, vList);
        });
        return multiValueMap;
    }

    public static <K, V> MultiValueMap<K, V> toMultiValueMap(Map<K, V> map) {
        return toMultiValueMap(map, true);
    }

    public static HttpHeaders toHttpHeaders(Map<String, String> httpHeaders) {
        return new HttpHeaders(toMultiValueMap(httpHeaders));
    }

}

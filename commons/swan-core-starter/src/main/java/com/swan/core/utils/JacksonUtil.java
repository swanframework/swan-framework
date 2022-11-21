package com.swan.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swan.core.exception.SwanBaseException;

import java.util.*;

/** jackson 工具类
 * @author zongf
 * @since 2022-11-21
 **/
public class JacksonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static JavaType collectionType(Class<?> collectionClz, Class<?> ...elementClz) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClz, elementClz);
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SwanBaseException("json 序列化异常", e);
        }
    }

    public static <T> T toObject(String json, Class<T> t) {
        try {
            return objectMapper.readValue(json, t);
        } catch (JsonProcessingException e) {
            throw new SwanBaseException("json 反序列化异常", e);
        }
    }

    public static <T> List<T> toList(String json, Class<T> clz) {
        try {
            return objectMapper.readValue(json, collectionType(List.class, clz));
        } catch (JsonProcessingException e) {
            throw new SwanBaseException(e);
        }
    }
    public static <K, V> Map<K, V> toMap(String json, Class<K> keyClz, Class<V> valueClz) {
        try {
            return objectMapper.readValue(json, collectionType(LinkedHashMap.class, keyClz, valueClz));
        } catch (JsonProcessingException e) {
            throw new SwanBaseException(e);
        }
    }

    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new SwanBaseException("json 反序列化异常", e);
        }
    }

}

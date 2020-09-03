package com.c332030.service.data.dict.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Service;

import org.reflections.Reflections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import lombok.Data;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

import com.c332030.constant.enumerable.annotation.GenerateDataDict;
import com.c332030.constant.enumerable.data.IDataDictEnum;
import com.c332030.model.sys.KeyValue;
import com.c332030.service.data.dict.IDataDictService;
import com.c332030.util.data.JSONUtils;

/**
 * <p>
 * Description: DataDictServiceImpl
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
@Service
public class DataDictServiceImpl implements IDataDictService {

    /**
     * 数据字典map
     */
    private static final Map<String, DataDict> DATA_DICT_MAP = new ConcurrentHashMap<>();

    DataDictServiceImpl() {

        var reflections = new Reflections(DATA_BASE_PACKAGE);
        var dataDictClassSet = reflections.getTypesAnnotatedWith(GenerateDataDict.class);
        dataDictClassSet.forEach(clazz -> {

            log.debug("Init IDataDictEnum {}", clazz.getName());

            if(!IDataDictEnum.class.isAssignableFrom(clazz)) {
                log.error("{} not implements IDataDictEnum", clazz.getName());
                return;
            }

            @SuppressWarnings("unchecked")
            var dataDict = (Class<IDataDictEnum>)clazz;
            add(dataDict);
        });

        log.info("DataDictServiceImpl Initialized");
    }

    public static void add(Class<IDataDictEnum> dataDictClass) {
        val dataDictName = IDataDictService.getDataDictName(dataDictClass);
        DATA_DICT_MAP.put(dataDictName, new DataDict(dataDictClass));
    }

    @Data
    public static class DataDict {

        public DataDict(Class<?> tClass) {
            this.tClass = tClass;

            var enums = (Enum<?>[])tClass.getEnumConstants();

            var dataDictBuilder = new ImmutableMap.Builder<String, IDataDictEnum>();
            var keyValuesBuilder = new ImmutableList.Builder<KeyValue>();

            for(var enumObj: enums) {

                var dataDictEnum = (IDataDictEnum)enumObj;
                var name = enumObj.name();
                var text = dataDictEnum.getText();

                dataDictBuilder.put(name, dataDictEnum);
                keyValuesBuilder.add(new KeyValue(name, text));
            }

            dataDictMap = dataDictBuilder.build();
            keyValues = keyValuesBuilder.build();

            keyValueJson = JSONUtils.toJson(keyValues);
        }

        /**
         * 类型
         */
        private final Class<?> tClass;

        /**
         * json 字符串
         */
        private final Map<String, IDataDictEnum> dataDictMap;

        /**
         * 键值对
         */
        private final List<KeyValue> keyValues;

        /**
         * json 字符串
         */
        private final String keyValueJson;
    }

    public DataDict getDataDict(String enumClassName) {
        return MapUtils.getObject(DATA_DICT_MAP, enumClassName);
    }

    @Override
    public String getValue(Class<IDataDictEnum> dataDictClass, String key) {
        return getValue(IDataDictService.getDataDictName(dataDictClass), key);
    }

    @Override
    public String getValue(String dataDictName, String key) {

        var dataDict = getDataDict(dataDictName);
        if(null == dataDict) {
            return StringUtils.EMPTY;
        }

        var dataDictEnum = MapUtils.getObject(dataDict.dataDictMap, key);
        return dataDictEnum == null ? StringUtils.EMPTY : dataDictEnum.getText();
    }
}

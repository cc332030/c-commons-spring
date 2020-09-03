package com.c332030.service.data.dict;

import lombok.NonNull;

import com.c332030.constant.enumerable.data.IDataDictEnum;
import com.c332030.constant.sys.SysConstants;

/**
 * <p>
 * Description: IDataDictService
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public interface IDataDictService {

    String DATA_BASE_PACKAGE = SysConstants.PackageName.C_BASE;

    /**
     * <p>
     * Description: 获取数据字典名称
     * </p>
     *
     * @param dataDictClass 数据字典类 class
     * @return 数据字典类名称
     * @author c332030
     */
    static String getDataDictName(@NonNull Class<IDataDictEnum> dataDictClass) {
        return dataDictClass.getSimpleName();
    }

    /**
     * <p>
     * Description: 获取值
     * </p>
     *
     * @param dataDictClass 数据字典类
     * @param key 键
     * @return 数据字典值
     * @author c332030
     */
    String getValue(@NonNull Class<IDataDictEnum> dataDictClass, @NonNull String key);

    /**
     * <p>
     * Description: 获取值
     * </p>
     *
     * @param dataDictName 数据字典名称
     * @param key 键
     * @return 数据字典值
     * @author c332030
     */
    String getValue(@NonNull String dataDictName, @NonNull String key);
}

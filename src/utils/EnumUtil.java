/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：工具类
 * @Package: utils 
 * @author: chengbao_0  
 * @date: 2020-7-21 17:19:39 
 */
package utils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @ClassName EnumUtil
 * @Desc 枚举工具类，负责检查枚举字段是否合法
 * @author chengbao_0
 * @Date 2020-7-21 17:19:39
 */
public class EnumUtil {
    /**
     * 校验枚举字段是否合法
     * @param targetField 枚举字段
     * @param clazz       枚举类型Class
     * @return 字段是否合法
     */
    public static <T> boolean validateField(String targetField, Class<T> clazz) {
    	//验证参数的有效性，确认clazz为枚举类型，同时待检验的字段不能为空
        if (clazz == null || !clazz.isEnum() || targetField.isEmpty()) {
            return false;
        }
        //验证枚举则断是否合法
        try {
            Method method = clazz.getMethod("valueOf", String.class);
            Object item = method.invoke(null, targetField);
            if (Objects.nonNull(item))
                return true;
        } catch (Exception ignored) {
        }
        return false;
    }
}

package com.system.backgroundmanagement.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Map工具类
 * </p>
 *
 * @author luoyuequan
 * @time 2020/1/6 14:20
 */
public class MapUtils {
    /**
     * 实体类转换为Map数据类型
     *
     * @param o 待转换的实体类
     * @return 对应的map
     */
    public static Map<String, Object> convertMap(Object o) {
        Map<String, Object> map = new HashMap<>(16);
        if (o == null) {
            return map;
        }
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(o);
                //属性对应的属性值不为null
                if (value != null) {
                    boolean b = value instanceof Boolean;
                    if (b) {
                        map.put("is_" + field.getName(), value);
                    } else {
                        map.put(field.getName(), value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}

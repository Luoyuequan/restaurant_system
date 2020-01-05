package com.system.backgroundmanagement.common;

/**
 * <p>数组常用工具类</p>
 *
 * @author luoyuequan
 * @date 2020/01/05
 * @time 上午 10:58
 */
public class ArraysUtils {
    /**
     * 校验数组是否为空
     *
     * @param array 待校验的数组
     * @return 检验结果
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }
}

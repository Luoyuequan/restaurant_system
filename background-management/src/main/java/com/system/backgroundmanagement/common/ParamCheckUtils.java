package com.system.backgroundmanagement.common;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 参数检查工具类
 * </p>
 *
 * @author luoyuequan
 * @date 2020/01/05
 * @time 下午 10:35
 */
@Slf4j
public class ParamCheckUtils {

    /**
     * 批量检查多个id的字符串,是否符合数据类型
     * 符合转换为list集合
     *
     * @param ids
     * @param idList
     * @return
     */
    @Nullable
    @Contract("null, _ -> !null")
    public static ResponseVO checkBatchIds(String ids, List<Long> idList) {
        if (ids == null) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        String[] strings = ids.split(",");
        //接收的参数是否缺少
        if (ArraysUtils.isEmpty(strings)) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        //转型并且校验接收的参数格式是否非法
        for (String s : strings) {
            try {
                long id = Long.parseLong(s);
                if (id <= 0) {
                    return ResponseVO.error(MessageEnum.VARIABLE_INVALID_ERROR);
                }
                idList.add(id);
            } catch (NumberFormatException e) {
                log.warn("批量删除,含有非法数字格式id:{}", s, e.getCause());
                return ResponseVO.error(MessageEnum.VARIABLE_INVALID_ERROR);
            }
        }
        //校验转型后的参数是否为空
        if (CollectionUtils.isEmpty(idList)) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        return null;
    }

    /**
     * 批量检查value是否为null
     *
     * @param values
     * @return
     */
    @Nullable
    @Contract("null -> !null")
    public static ResponseVO checkValues(Object... values) {
        if (ArraysUtils.isEmpty(values)) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        //检查多个value是否非空
        for (Object v : values) {
            if (v == null) {
                return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
            }
        }
        return null;
    }
}

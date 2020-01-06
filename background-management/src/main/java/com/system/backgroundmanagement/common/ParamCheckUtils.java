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

    @Nullable
    @Contract("null, _ -> !null")
    public static ReturnVO checkBatchIds(String ids, List<Long> idList) {
        if (ids == null) {
            return ReturnVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        String[] strings = ids.split(",");
        //接收的参数是否缺少
        if (ArraysUtils.isEmpty(strings)) {
            return ReturnVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        //转型并且校验接收的参数格式是否非法
        for (String s : strings) {
            try {
                Long id = Long.valueOf(s);
                idList.add(id);
            } catch (NumberFormatException e) {
                log.warn("批量删除,含有非法数字格式id:{}", s, e.getCause());
                return ReturnVO.error(MessageEnum.VARIABLE_INVALID_ERROR);
            }
        }
        //校验转型后的参数是否为空
        if (CollectionUtils.isEmpty(idList)) {
            return ReturnVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        return null;
    }

}

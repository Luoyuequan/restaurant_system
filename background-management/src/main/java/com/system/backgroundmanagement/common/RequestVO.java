package com.system.backgroundmanagement.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 接受前端参数
 * </p>
 *
 * @author luoyuequan
 * @date 2020/01/04
 * @time 下午 10:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RequestVO {
    /**
     * 根据id查询
     */
    private Long id;

    /**
     * 栏目id
     */
    private Long columnId;

    /**
     * 批量操作时,多个id由英文逗号,分割
     */
    private String ids;
    /**
     * 名字
     */
    private String name;
    /**
     * 标题
     */
    private String title;
    /**
     * 是否推荐
     */
    private Boolean recommend;
    /**
     * 是否置顶
     */
    private Boolean top;
    /**
     * 排序列名，优先级规则等同数据库order by 规则
     * 多个列名由英文,分割
     */
//    private String sortColumnName;

}

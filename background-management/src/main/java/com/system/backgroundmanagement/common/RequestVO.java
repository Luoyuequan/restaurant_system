package com.system.backgroundmanagement.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("前端请求参数体")
public class RequestVO {
    /**
     * 根据id查询
     */
    @ApiModelProperty(value = "对象id(仅用于get,put,delete)", example = "0", allowEmptyValue = true)
    private Long id;

    /**
     * 栏目id
     */
    @ApiModelProperty(value = "栏目对象id(仅用于栏目列表获取)", example = "0", allowEmptyValue = true)
    private Long columnId;

    /**
     * 批量操作时,多个id由英文逗号,分割
     */
    @ApiModelProperty(value = "根据id批量删除(多个id英文逗号分割)")
    private String ids;
    /**
     * 名字
     */
    @ApiModelProperty(value = "名字")
    private String name;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 是否推荐
     */
    @ApiModelProperty(value = "更改推荐状态")
    private Boolean recommend;
    /**
     * 是否置顶
     */
    @ApiModelProperty(value = "更改置顶状态")
    private Boolean top;

    @ApiModelProperty(value = "起始时间戳(登录日志查询)")
    private Long startTime;

    @ApiModelProperty(value = "截止时间戳(登录日志查询)")
    private Long endTime;

}

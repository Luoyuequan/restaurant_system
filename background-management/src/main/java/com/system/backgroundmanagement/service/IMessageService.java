package com.system.backgroundmanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.system.backgroundmanagement.common.PageVO;
import com.system.backgroundmanagement.common.VO;
import com.system.backgroundmanagement.entity.Message;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 留言表 服务类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
public interface IMessageService extends IService<Message> {

    /**
     * 根据获取留言消息列表
     *
     * @param pageVo 分页参数
     * @param vo     条件参数
     * @return 结果
     */
    IPage<Message> listMessage(PageVO pageVo, VO vo) throws RuntimeException;

    /**
     * 根据id删除留言消息
     * 可批量删除
     *
     * @param idList ids
     * @return 删除结果
     */
    boolean deleteByIds(@NotNull List<Long> idList);
}

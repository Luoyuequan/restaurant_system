package com.system.backgroundmanagement.dao;

import java.io.Serializable;

/**
 * DAO公共基类，由MybatisGenerator自动生成请勿修改
 *
 * @param <Model> The Model Class 这里是泛型不是Model类
 * @param <PK>    The Primary Key Class 如果是无主键，则可以用Model来跳过，如果是多主键则是Key类
 *                // * @param <E> The Example Class
 */
public interface BaseDao<Model, PK extends Serializable> {

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(PK id);

    /**
     * 全字段添加
     *
     * @param record
     * @return
     */
    int insert(Model record);

    /**
     * 全字段动态添加
     *
     * @param record
     * @return
     */
    int insertSelective(Model record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    Model selectByPrimaryKey(PK id);

    /**
     * 根据主键全字段动态修改
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Model record);

    /**
     * 根据主键全字段修改
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Model record);
}
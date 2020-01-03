package com.system.frontmanagement.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * column_type
 * @author 
 */
@Data
public class ColumnType implements Serializable {
    private Integer id;

    /**
     * 栏目类型值
     */
    private String value;

    private static final long serialVersionUID = 1L;
}
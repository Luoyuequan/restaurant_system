package com.system.backgroundmanagement.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * column_type
 *
 * @author
 */
@Data
public class ColumnType implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 栏目类型值
     */
    private String value;
}
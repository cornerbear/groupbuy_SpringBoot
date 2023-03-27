package com.xj.groupbuy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("COMMUNITY")
public class Community implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;

    /**
     * 街道
     */
    @TableField("STREET_CODE")
    private Long streetCode;

    /**
     * 社区名
     */
    @TableField("NAME")
    private String name;

    /**
     * 详细位置
     */
    @TableField("DETAIL_LOCATION")
    private String detailLocation;


}

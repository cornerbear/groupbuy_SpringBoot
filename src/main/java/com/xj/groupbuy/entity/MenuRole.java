package com.xj.groupbuy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("menu_role")
public class MenuRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("MENU_ID")
    private Integer menuId;

    @TableField("ROLE_ID")
    private Integer roleId;


}

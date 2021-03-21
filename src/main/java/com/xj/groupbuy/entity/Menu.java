package com.xj.groupbuy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("URL")
    private String url;

    @TableField("PATH")
    private String path;

    @TableField("COMPONENT")
    private String component;

    @TableField("NAME")
    private String name;

    @TableField("ICON_CLS")
    private String iconCls;

    @TableField("PARENT_ID")
    private String parentId;

    @TableField("ENABLED")
    private Boolean enabled;

    @TableField(exist = false)
    private Meta meta;
    
    @TableField(exist = false)
    private List<Menu> children;

    @TableField(exist = false)
    private List<Role> roles;
    

}

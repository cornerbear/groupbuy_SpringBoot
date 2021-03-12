package com.xj.groupbuy.background.user.entity;

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

    @TableField("KEEP_ALIVE")
    private String keepAlive;

    @TableField("REQUIRE_AUTH")
    private String requireAuth;

    @TableField("PARENT_ID")
    private Integer parentId;

    @TableField("ICON_CLS")
    private String iconCls;

    @TableField(exist = false)
    private List<Role> roles;
    

}

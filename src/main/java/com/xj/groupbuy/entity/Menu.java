package com.xj.groupbuy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
public class Menu implements Serializable,TreeEntity<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;

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
    private Integer parentId;

    @TableField("ENABLED")
    private Boolean enabled;
    
    @TableField("SORT")
    private Integer sort;

    @TableField(exist = false)
    private Meta meta;
    
    @TableField(exist = false)
    private List<Menu> children;

    @TableField(exist = false)
    private List<Role> roles;


    @Override
    @JsonIgnore
    public Integer getTreeId() {
        return this.id;
    }

    @Override
    @JsonIgnore
    public Integer getTreeParentId() {
        return this.parentId;
    }

    @Override
    @JsonIgnore
    public boolean isTreeEnabled() {
        return this.enabled;
    }

    @Override
    public void setTreeChildren(List<Menu> children) {
        this.children = children;
    }
}

package com.xj.groupbuy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CATEGORY")
public class Category implements Serializable,TreeEntity<Category> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CAT_ID",type = IdType.AUTO)
    private Integer catId;

    @TableField("CAT_NAME")
    private String catName;

    @TableField("PARENT_CAT_ID")
    private Integer parentCatId;

    @TableField("SORT")
    private int sort;

    @TableField(exist = false)
    private List<Category> children;

    @Override
    public Integer getTreeId() {
        return this.catId;
    }

    @Override
    public Integer getTreeParentId() {
        return this.parentCatId;
    }

    @Override
    public void setTreeChildren(List<Category> children) {
        this.children = children;
    }
}

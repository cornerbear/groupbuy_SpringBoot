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
 * @since 2021-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("USER_ROLE")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    
    @TableField("USER_ID")
    private String userId;

    @TableField("ROLE_ID")
    private Integer roleId;


    public UserRole(String userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}

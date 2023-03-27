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
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("USER_APPLY")
public class UserApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    
    @TableField("USER_ID")
    private String userId;

    @TableField("ROLE")
    private String role;


    public UserApply(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }
}

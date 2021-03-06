package com.xj.groupbuy.backstage.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("USER_ID")
    private String userId;

    @TableField("USER_NAME")
    private String userName;

    @TableField("USER_PWD")
    private String userPwd;


}

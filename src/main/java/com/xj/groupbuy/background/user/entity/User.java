package com.xj.groupbuy.background.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("USER_ID")
    private String userId;

    @TableField("USER_NAME")
    private String userName;

    @TableField("USER_PWD")
    private String userPwd;

    @TableField("USER_SEX")
    private Integer userSex;

    @TableField("USER_PHONE")
    private String userPhone;

    @TableField("USER_ADDRESS")
    private String userAddress;


}

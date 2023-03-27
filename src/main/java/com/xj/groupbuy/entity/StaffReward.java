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
 * @since 2021-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("STAFF_REWARD")
public class StaffReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;

    @TableField("USER_ID")
    private String userId;
    
    @TableField("REWARD_NAME")
    private String rewardName;

    @TableField("REWARD_DESC")
    private String rewardDesc;

    @TableField("CREATE_TIME")
    private String createTime;

    @TableField("ACTION_USER_ID")
    private String actionUserId;


}

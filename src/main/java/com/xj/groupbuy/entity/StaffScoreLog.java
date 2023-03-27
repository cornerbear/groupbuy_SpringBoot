package com.xj.groupbuy.entity;

import java.math.BigDecimal;

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
 * ?.
 * @author zhangxiaojian
  q1`
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("STAFF_SCORE_LOG")
public class StaffScoreLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    private String ID;
    
    @TableField("USER_ID")
    private String userId;

    /**
     * 操作前分数
     */
    @TableField("CHANGE_SCORE")
    private BigDecimal changeScore;
    
    /**
     * 操作前分数
     */
    @TableField("BEFORE_SCORE")
    private BigDecimal beforeScore;

    /**
     * 操作后分数
     */
    @TableField("AFTER_SCORE")
    private BigDecimal afterScore;

    /**
     * 操作人ID
     */
    @TableField("ACTION_USER_ID")
    private String actionUserId;

    /**
     * 分数变更原因
     */
    @TableField("REASON")
    private String reason;

    /**
     * 记录时间
     */
    @TableField("LOG_TIME")
    private String logTime;

    /**
     * 1增长，0减少
     */
    @TableField("ACTION")
    private Boolean action;


}

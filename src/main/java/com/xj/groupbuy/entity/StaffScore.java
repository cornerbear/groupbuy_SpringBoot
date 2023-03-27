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
 *
 * @author zhangxiaojian
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("STAFF_SCORE")
public class StaffScore implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    private String ID;
    
    /**
     * 员工ID
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 员工奖惩考核分数
     */
    @TableField("SCORE")
    private BigDecimal score;


    public StaffScore(String userId) {
        this.userId = userId;
        this.score = BigDecimal.valueOf(100);
    }
}

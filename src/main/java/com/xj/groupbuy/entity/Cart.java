package com.xj.groupbuy.entity;

import java.math.BigDecimal;

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
 * @since 2021-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CART")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;

    @TableField("USER_ID")
    private String userId;

    @TableField("TOTAL_PRICE")
    private BigDecimal totalPrice;

    @TableField("GOODS_NUM")
    private Integer goodsNum;

    @TableField(exist = false)
    private List<CartItem> cartItems;

    public Cart(String userId) {
        this.userId = userId;
        this.totalPrice = BigDecimal.ZERO;
        this.goodsNum = 0;
    }
}

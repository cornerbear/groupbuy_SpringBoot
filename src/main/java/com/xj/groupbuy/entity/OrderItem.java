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
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ORDER_ITEM")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;

    @TableField("ORDER_ID")
    private Integer orderId;

    @TableField("GOODS_ID")
    private Integer goodsId;

    @TableField("GOODS_NUM")
    private Integer goodsNum;

    @TableField("GOODS_PRICE")
    private BigDecimal goodsPrice;

    @TableField("GOODS_UNIT")
    private String goodsUnit;

    @TableField("SPEC_KEY")
    private String specKey;

    @TableField("SPEC_KEY_NAME")
    private String specKeyName;

    public OrderItem(){}

    public OrderItem(Integer orderId , CartItem cartItem) {
        this.orderId = orderId;
        this.goodsId = cartItem.getGoodsId();
        this.goodsNum = cartItem.getGoodsNum();
        this.specKey = cartItem.getSpecKey();
        this.specKeyName = cartItem.getSpecKeyName();
    }
}

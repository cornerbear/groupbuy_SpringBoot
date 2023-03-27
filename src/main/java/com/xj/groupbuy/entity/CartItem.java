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
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CART_ITEM")
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    
    @TableField("CART_ID")
    private Integer cartId;

    @TableField("GOODS_ID")
    private Integer goodsId;
    
    @TableField("GOODS_STORE_ID")
    private String goodsStoreId;
    
    @TableField("GOODS_NUM")
    private Integer goodsNum;

    @TableField("SPEC_KEY")
    private String specKey;

    @TableField("SPEC_KEY_NAME")
    private String specKeyName;

    public CartItem(Integer cartId, Integer goodsId, String goodsStoreId) {
        this.cartId = cartId;
        this.goodsId = goodsId;
        this.goodsStoreId = goodsStoreId;
    }
}

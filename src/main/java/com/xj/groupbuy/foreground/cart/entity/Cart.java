package com.xj.groupbuy.foreground.cart.entity;

import java.math.BigDecimal;
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
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CART_ID")
    private String cartId;

    @TableField("USER_ID")
    private String userId;

    @TableField("TOTAL_PRICE")
    private BigDecimal totalPrice;

    @TableField("GOODS_NUM")
    private Integer goodsNum;


}

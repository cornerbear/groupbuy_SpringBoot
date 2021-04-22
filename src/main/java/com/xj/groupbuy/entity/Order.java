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
@TableName("`order`")
public class Order implements Serializable,Cloneable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    
    @TableField("USER_ID")
    private String userId;

    @TableField("STORE_ID")
    private String storeId;

    @TableField("PARENT_ORDER_ID")
    private Integer parentOrderId;

    @TableField("COUPON_ID")
    private Integer couponId;

    @TableField("CREATE_TIME")
    private String createTime;

    @TableField("CONSIGNEE")
    private String consignee;

    @TableField("ADDRESS")
    private String address;

    /**
     * 折扣
     */
    @TableField("DISCOUNT")
    private String discount;

    @TableField("USE_INTEGRAL")
    private String useIntegral;

    @TableField("USE_INTEGRAL_MONEY")
    private String useIntegralMoney;

    @TableField("PHONE")
    private String phone;

    @TableField("TOTAL_PRICE")
    private BigDecimal totalPrice;

    @TableField("GOODS_PRICE")
    private BigDecimal goodsPrice;

    @TableField("SHIPPING_PRICE")
    private BigDecimal shippingPrice;

    @TableField("PAY_PRICE")
    private BigDecimal payPrice;

    @TableField("COUPON_PRICE")
    private BigDecimal couponPrice;

    @TableField("ORDER_STATUS")
    private String orderStatus;

    @TableField("PAY_CODE")
    private String payCode;

    @TableField("PAY_NAME")
    private String payName;

    @TableField("PAY_TIME")
    private String payTime;

    @TableField("SHIPPING_CODE")
    private String shippingCode;

    @TableField("SHIPPING_NAME")
    private String shippingName;

    @TableField("SHIPPING_STATUS")
    private String shippingStatus;

    @TableField("SHIPPING_TIME")
    private String shippingTime;

    @TableField("USER_NOTE")
    private String userNote;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

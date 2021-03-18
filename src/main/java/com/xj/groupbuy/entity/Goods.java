package com.xj.groupbuy.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-03-08
 */
@Data
@TableName("GOODS")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "GOODS_ID",type = IdType.ASSIGN_ID)
    private String goodsId;

    @TableField("GOODS_STORE_ID")
    private String goodsStoreId;

    @TableField("GOODS_NAME")
    private String goodsName;

    @TableField("GOODS_CAT1")
    private String goodsCat1;

    @TableField("GOODS_CAT2")
    private String goodsCat2;

    @TableField("GOODS_CAT3")
    private String goodsCat3;

    @TableField("GOODS_EASY_DESC")
    private String goodsEasyDesc;

    @TableField("GOODS_DESC")
    private String goodsDesc;

    @TableField("GOODS_STOCK")
    private Integer goodsStock;

    @TableField("GOODS_WEIGHT")
    private Integer goodsWeight;

    @TableField("GOODS_UNIT")
    private String goodsUnit;

    @TableField("GOODS_PRICE")
    private BigDecimal goodsPrice;

    @TableField("GOODS_IMG")
    private String goodsImg;

    /**
     * 是否上架
     */
    @TableField("IS_ON_SALE")
    private Integer isOnSale;

    /**
     * 是否包邮0否1是
     */
    @TableField("IS_FREE_SHIPPING")
    private Integer isFreeShipping;

    /**
     * 是否推荐
     */
    @TableField("IS_RECOMMEND")
    private Integer isRecommend;

    /**
     * 是否新品
     */
    @TableField("IS_NEW")
    private Integer isNew;

    /**
     * 上架时间
     */
    @TableField("ON_TIME")
    private String onTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private String updateTime;

    /**
     * 售出数量
     */
    @TableField("SALE_COUNT")
    private Integer saleCount;

    /**
     * 奖励积分
     */
    @TableField("INTEGRAL")
    private Integer integral;


}

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

    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;

    @TableField("GOODS_STORE_ID")
    private String goodsStoreId;

    @TableField("GOODS_NAME")
    private String goodsName;

    @TableField("GOODS_CAT")
    private Integer goodsCat;

    @TableField("GOODS_EASY_DESC")
    private String goodsEasyDesc;

    @TableField("GOODS_DESC")
    private String goodsDesc;

    @TableField("GOODS_STOCK")
    private Integer goodsStock;

    @TableField("GOODS_WEIGHT")
    private String goodsWeight;

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
    private Boolean isOnSale;

    /**
     * 是否包邮0否1是
     */
    @TableField("IS_FREE_SHIPPING")
    private Boolean isFreeShipping;

    /**
     * 是否推荐
     */
    @TableField("IS_RECOMMEND")
    private Boolean isRecommend;

    /**
     * 是否新品
     */
    @TableField("IS_NEW")
    private Boolean isNew;

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

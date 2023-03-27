package com.xj.groupbuy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xj.groupbuy.common.util.DateUtil;
import com.xj.groupbuy.common.util.UserUtil;
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
@TableName("COMMUNITY_GOODS")
public class CommunityGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;

    @TableField("COMMUNITY_ID")
    private Integer communityId;

    @TableField("GOODS_ID")
    private Integer goodsId;

    @TableField("ACTION_USER_ID")
    private String actionUserId;

    @TableField("ADD_TIME")
    private String addTime;


    public CommunityGoods(Integer id, Integer communityId) {
        this.goodsId = id;
        this.communityId = communityId;
        this.addTime = DateUtil.getCurrentDate();
        this.actionUserId = UserUtil.getUserId();
    }
}

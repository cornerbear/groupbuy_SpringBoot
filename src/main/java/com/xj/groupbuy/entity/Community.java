package com.xj.groupbuy.entity;

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
 * @since 2021-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Community implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 省
     */
    @TableField("PROVINCE")
    private String province;

    /**
     * 市
     */
    @TableField("CITY")
    private String city;

    /**
     * 区
     */
    @TableField("AREA")
    private String area;

    /**
     * 街道
     */
    @TableField("STREET")
    private String street;

    /**
     * 社区名
     */
    @TableField("NAME")
    private String name;

    /**
     * 详细位置
     */
    @TableField("DETAIL_LOCATION")
    private String detailLocation;


}

package com.xj.groupbuy.mapper;

import com.xj.groupbuy.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-21
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    List<?> getOrderItems(Integer orderId);
}

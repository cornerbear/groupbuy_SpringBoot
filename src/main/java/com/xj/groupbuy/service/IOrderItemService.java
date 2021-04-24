package com.xj.groupbuy.service;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-21
 */
public interface IOrderItemService extends IService<OrderItem> {

    CommonVO getOrderItems(Integer orderId);
}

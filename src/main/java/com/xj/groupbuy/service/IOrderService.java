package com.xj.groupbuy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.ui.Model;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-21
 */
public interface IOrderService extends IService<Order> {

    CommonVO createOrder(Order order);

    CommonVO createOrderDetail();

    IPage<?> getTable(Integer pageNo, Integer pageSize);

    CommonVO payForOrder(Integer orderId);

    CommonVO cancelOrder(Integer orderId);

    IPage<?> getStoreOrderTable(String storeId, Integer pageNo, Integer pageSize);

    CommonVO deliver(Map<String,String> map);

    CommonVO confirmArrived(Integer orderId);

    CommonVO confirmReceive(Integer orderId);

    CommonVO confirmOrderFinish(Integer orderItemId);
}

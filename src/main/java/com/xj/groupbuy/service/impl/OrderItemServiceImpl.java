package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.entity.OrderItem;
import com.xj.groupbuy.mapper.OrderItemMapper;
import com.xj.groupbuy.service.IOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-21
 */
@Service
@Primary
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

}

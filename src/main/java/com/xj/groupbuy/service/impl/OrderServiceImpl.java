package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Order;
import com.xj.groupbuy.mapper.OrderMapper;
import com.xj.groupbuy.service.IOrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    public CommonVO createOrder() {
        return null;
    }
}

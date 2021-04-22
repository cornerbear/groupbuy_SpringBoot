package com.xj.groupbuy.controller.user;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Order;
import com.xj.groupbuy.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/21
 */
@RestController
@RequestMapping("user/order")
public class UserOrderController {
    
    @Autowired
    private IOrderService orderService;
    
    @PostMapping
    public CommonVO createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }
    
    @GetMapping("createOrderDetail")
    public CommonVO getCreateOrderDetail(){
        return orderService.createOrderDetail();
    }
}

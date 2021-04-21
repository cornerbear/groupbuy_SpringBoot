package com.xj.groupbuy.controller.user;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonVO createOrder(){
        return orderService.createOrder();
    }
}

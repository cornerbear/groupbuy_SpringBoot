package com.xj.groupbuy.controller.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xj.groupbuy.common.util.UserUtil;
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

    
    @PutMapping("payForOrder/{orderId}")
    public CommonVO payForOrder(@PathVariable Integer orderId){
        return orderService.payForOrder(orderId);
    }

    @PutMapping("cancelOrder/{orderId}")
    public CommonVO cancelOrder(@PathVariable Integer orderId){
        return orderService.cancelOrder(orderId);
    }
    
    @PostMapping
    public CommonVO createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }
    
    @GetMapping("createOrderDetail")
    public CommonVO getCreateOrderDetail(){
        return orderService.createOrderDetail();
    }

    @GetMapping("all/{pageNo}/{pageSize}")
    public IPage<?> getTable(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize){

        return orderService.getTable(pageNo,pageSize);
    }
}

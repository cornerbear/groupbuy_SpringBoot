package com.xj.groupbuy.controller.store;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Order;
import com.xj.groupbuy.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/23
 */
@RestController
@RequestMapping("store/order")
public class StoreOrderController {
    
    @Autowired
    private IOrderService orderService;

    @PutMapping("confirmArrived/{orderId}")
    public CommonVO confirmArrived(@PathVariable Integer orderId){
        return orderService.confirmArrived(orderId);
    }
    
    @PutMapping("deliver")
    public CommonVO deliver(@RequestBody Map<String,String> map){
        return orderService.deliver(map);
    }
    
    @GetMapping("all/{pageNo}/{pageSize}")
    public IPage<?> getStoreOrderTable(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize){

        return orderService.getStoreOrderTable(UserUtil.getUserId(),pageNo,pageSize);
    }
    
}

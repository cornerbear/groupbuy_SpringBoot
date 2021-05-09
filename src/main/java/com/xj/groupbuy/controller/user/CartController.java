package com.xj.groupbuy.controller.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.CartItem;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/21
 */
@RestController
@RequestMapping("user/cart")
public class CartController {

    @Autowired
    private ICartService cartService;
    

    @GetMapping("checkHaveGoods")
    public CommonVO checkHaveGoods(){
        return cartService.checkHaveGoods();
    }
    @PostMapping("{goodsId}")
    public CommonVO addGoodsToCart(@PathVariable Integer goodsId){
        return cartService.addGoodsToCart(goodsId);
    }
    
    @DeleteMapping("{cartItemId}")
    public CommonVO removeGoodsFromCart(@PathVariable Integer cartItemId){
        return cartService.removeGoodsFromCart(cartItemId);
    }
    
    @GetMapping("all/{pageNo}/{pageSize}")
    public CommonVO getUserCart(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize){

        return cartService.haveUserCartWithItems(pageNo,pageSize);
    }

}

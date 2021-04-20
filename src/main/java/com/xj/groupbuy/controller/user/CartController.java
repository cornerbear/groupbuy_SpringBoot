package com.xj.groupbuy.controller.user;


import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.service.ICartService;
import com.xj.groupbuy.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-07
 */
@RestController
@RequestMapping("user/cart")
public class CartController {

    @Autowired
    private ICartService cartService;
    @Autowired
    private IGoodsService goodsService;
    
    @PostMapping("{goodsId}")
    public CommonVO addGoodsToCart(@PathVariable Integer goodsId){
        return cartService.addGoodsToCart(goodsId);
    }
    
}

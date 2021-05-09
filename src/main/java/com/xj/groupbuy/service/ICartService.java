package com.xj.groupbuy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xj.groupbuy.entity.CartItem;
import com.xj.groupbuy.entity.Goods;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-07
 */
public interface ICartService extends IService<Cart> {

    CommonVO addGoodsToCart(Integer goodsId);

    Cart haveUserCart();

    CommonVO haveUserCartWithItems(Integer pageNo,Integer pageSize);

    CommonVO removeGoodsFromCart(Integer cartItemId);

    Boolean clearCart(Integer cartId);

    CommonVO checkHaveGoods();
}

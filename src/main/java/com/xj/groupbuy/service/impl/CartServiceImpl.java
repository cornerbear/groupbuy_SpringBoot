package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Cart;
import com.xj.groupbuy.entity.CartItem;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.mapper.CartItemMapper;
import com.xj.groupbuy.mapper.CartMapper;
import com.xj.groupbuy.mapper.GoodsMapper;
import com.xj.groupbuy.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-07
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    
    @Override
    public CommonVO addGoodsToCart(Integer goodsId) {
        Cart cart = this.getUserCart(UserUtil.getUserId());
        Goods goods = goodsMapper.selectById(goodsId);

        if(cart == null){
            // 用户还没有购物车，创建
            cart = new Cart(UserUtil.getUserId());
            cartMapper.insert(cart);
        }

        Integer cartId = cart.getId();
        
        // 查看购物车是否已经有
        int flag;
        if(cartItemMapper.selectCount(new QueryWrapper<CartItem>().eq("cart_id",cartId).eq("goods_id",goodsId)) == 0){
            // 说明没有
            CartItem cartItem = new CartItem(cartId, goodsId);
            flag = cartItemMapper.insert(cartItem);
        } else {
            // 说明购物车已经有了，只需+1
            CartItem cartItem = cartItemMapper.selectOne(new QueryWrapper<CartItem>().eq("cart_id", cartId).eq("goods_id", goodsId));
            cartItem.setGoodsNum(cartItem.getGoodsNum()+1);
            flag = cartItemMapper.updateById(cartItem);
        }
        
        // 修改购物车信息
        cart.setTotalPrice(cart.getTotalPrice().add(goods.getGoodsPrice()));
        cart.setGoodsNum(cart.getGoodsNum()+1);
        cartMapper.updateById(cart);
        
        return new CommonVO(flag==1,flag==1?"添加购物车成功":"添加购物车失败");
    }

    @Override
    public Cart getUserCart(String userId) {
        return cartMapper.selectOne(new QueryWrapper<Cart>().eq("user_id",userId));
    }

    @Override
    public CommonVO getUserCartWithItems(Integer pageNo,Integer pageSize) {
        Cart cart = this.getUserCart(UserUtil.getUserId());
        IPage<?> cartItems = cartItemMapper.getCartItems(cart.getId(),new Page<>(pageNo,pageSize));
        cart.setCartItems(cartItems);
        return new CommonVO(true,cart);
    }
}

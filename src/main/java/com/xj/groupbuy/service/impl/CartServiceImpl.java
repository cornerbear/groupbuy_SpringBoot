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

import java.util.HashMap;
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
        Cart cart = this.haveUserCart();
        Goods goods = goodsMapper.selectById(goodsId);

        Integer cartId = cart.getId();
        
        // 查看购物车是否已经有
        int flag;
        if(cartItemMapper.selectCount(new QueryWrapper<CartItem>().eq("cart_id",cartId).eq("goods_id",goodsId)) == 0){
            // 说明没有
            CartItem cartItem = new CartItem(cartId, goodsId,goods.getGoodsStoreId());
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
    public CommonVO removeGoodsFromCart(Integer cartItemId) {
        cartItemMapper.deleteById(cartItemId);
        return new CommonVO(true,"从购物车移除成功");
    }

    @Override
    public Boolean clearCart(Integer cartId) {
        
        cartMapper.deleteById(cartId);
        cartItemMapper.delete(new QueryWrapper<CartItem>().eq("cart_id",cartId));
        
        return true;
    }

    @Override
    public CommonVO checkHaveGoods() {
        Cart cart = this.haveUserCart();
        Integer id = cart.getId();
        Integer goodsCount = cartItemMapper.selectCount(new QueryWrapper<CartItem>().eq("cart_id", id));
        boolean flag = goodsCount != 0;
        return new CommonVO(flag,flag?"":"当前无商品");
    }

    @Override
    public Cart haveUserCart() {
        String userId = UserUtil.getUserId();
        Cart cart = cartMapper.selectOne(new QueryWrapper<Cart>().eq("user_id", userId));

        if(cart == null){
            // 用户还没有购物车，创建
            cart = new Cart(userId);
            cartMapper.insert(cart);
        }
        return cart;
    }

    @Override
    public CommonVO haveUserCartWithItems(Integer pageNo,Integer pageSize) {
        Cart cart = this.haveUserCart();
        IPage<?> cartItems = cartItemMapper.getCartItems(cart.getId(),new Page<>(pageNo,pageSize));
        Map<String,Object> map = new HashMap<>();
        map.put("cart",cart);
        map.put("cartItems",cartItems);
        return new CommonVO(true,map);
    }

}

package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.util.CloneUtil;
import com.xj.groupbuy.common.util.DateUtil;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.*;
import com.xj.groupbuy.mapper.*;
import com.xj.groupbuy.service.ICartService;
import com.xj.groupbuy.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CommunityMapper communityMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ICartService cartService;
    
    @Override
    public CommonVO createOrder(Order order) {
        String userId = UserUtil.getUserId();
        Cart cart = cartMapper.selectCartWithItems(userId);
        List<CartItem> cartItems = cart.getCartItems();

        order.setUserId(userId);
        order.setCreateTime(DateUtil.getCurrentDate());
        order.setOrderStatus("0");
        
        // 分订单
        Set<String> storeIds = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            storeIds.add(cartItem.getGoodsStoreId());
        }
        if(storeIds.size() == 1){
            // 单商户，不用分订单
            order.setParentOrderId(0);
            order.setStoreId(cartItems.get(0).getGoodsStoreId());
            this.calculateOrderPrice(order,null,cartItems);

            orderMapper.insert(order);
            // 保存订单项
            this.saveOrderItem(order.getId(),cartItems,null);
            
        } else {
            order.setParentOrderId(0);
            order.setStoreId("0");
            
            this.calculateOrderPrice(order,null,cartItems);
            
            orderMapper.insert(order);
            
            Integer parentOrderId = order.getId();
            
            for (String storeId : storeIds) {
                
                Order sonOrder = CloneUtil.clone(order,Order.class);
                sonOrder.setStoreId(storeId);
                sonOrder.setParentOrderId(parentOrderId);
                
                this.calculateOrderPrice(sonOrder,storeId,cartItems);
                
                orderMapper.insert(sonOrder);
                
                // 保存订单项
                this.saveOrderItem(order.getId(),cartItems,storeId);
            } 
        }
        
        // 清空购物车
        Boolean clearFlag = cartService.clearCart(cart.getId());

        return new CommonVO(true,"生成订单成功");
    }

    private void saveOrderItem(Integer orderId, List<CartItem> cartItems, String storeId) {
        for (CartItem cartItem : cartItems) {

            // 如果不是该商店的就跳过
            if(storeId != null && !cartItem.getGoodsStoreId().equals(storeId))
                continue;

            OrderItem orderItem = new OrderItem(orderId,cartItem);

            Goods goods = goodsMapper.selectById(cartItem.getGoodsId());
            orderItem.setGoodsPrice(goods.getGoodsPrice());
            orderItem.setGoodsUnit(goods.getGoodsUnit());
            orderItemMapper.insert(orderItem);
        }
    }

    @Override
    public CommonVO createOrderDetail() {

        User user = UserUtil.getCurrentUser();
        Cart cart = cartMapper.selectCartWithItems(user.getUserId());
        List<CartItem> cartItems = cart.getCartItems();
        Order order = new Order();

        // 设置价钱等信息
        this.calculateOrderPrice(order,null,cartItems);

        // 设置其他信息
        Map<String, String> parentLevel = communityMapper.getParentLevelName(user.getCommunityId());
        String address = parentLevel.get("provinceName")+parentLevel.get("cityName")+parentLevel.get("areaName")+parentLevel.get("streetName");
        Community community = communityMapper.selectById(user.getCommunityId());
        address += community.getDetailLocation()+community.getName();
        order.setAddress(address);
        
        order.setConsignee(user.getName());
        
        order.setPhone(user.getPhone());
        
        return new CommonVO(true,order);
    }

    @Override
    public IPage<?> getTable(Integer pageNo, Integer pageSize) {
        Page<Order> page = new Page<>(pageNo, pageSize);
        return orderMapper.getTable(page);
    }

    private void calculateOrderPrice(Order order,String storeId,List<CartItem> cartItems){
        BigDecimal goodsPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            
            // 如果不是该商店的就跳过
            if(storeId != null && !cartItem.getGoodsStoreId().equals(storeId))
                continue;
            Integer goodsId = cartItem.getGoodsId();
            Goods goods = goodsMapper.selectById(goodsId);
            goodsPrice = goodsPrice.add(goods.getGoodsPrice().multiply(new BigDecimal(cartItem.getGoodsNum())));
        }
        order.setGoodsPrice(goodsPrice);
        order.setPayPrice(goodsPrice);
    }
}

package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.entity.Cart;
import com.xj.groupbuy.mapper.CartMapper;
import com.xj.groupbuy.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

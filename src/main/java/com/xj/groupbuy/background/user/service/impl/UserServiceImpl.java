package com.xj.groupbuy.background.user.service.impl;

import com.xj.groupbuy.background.user.entity.User;
import com.xj.groupbuy.background.user.mapper.UserMapper;
import com.xj.groupbuy.background.user.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

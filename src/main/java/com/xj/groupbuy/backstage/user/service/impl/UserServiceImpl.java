package com.xj.groupbuy.backstage.user.service.impl;

import com.xj.groupbuy.backstage.user.entity.User;
import com.xj.groupbuy.backstage.user.mapper.UserMapper;
import com.xj.groupbuy.backstage.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

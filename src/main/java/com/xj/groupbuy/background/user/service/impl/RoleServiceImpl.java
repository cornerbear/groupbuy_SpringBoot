package com.xj.groupbuy.background.user.service.impl;

import com.xj.groupbuy.background.user.entity.Role;
import com.xj.groupbuy.background.user.mapper.RoleMapper;
import com.xj.groupbuy.background.user.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-11
 */
@Service
@Primary
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}

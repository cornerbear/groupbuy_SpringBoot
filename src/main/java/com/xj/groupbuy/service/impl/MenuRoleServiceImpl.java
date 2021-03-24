package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.entity.MenuRole;
import com.xj.groupbuy.mapper.MenuRoleMapper;
import com.xj.groupbuy.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-24
 */
@Service
@Primary
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

}

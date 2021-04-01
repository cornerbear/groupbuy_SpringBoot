package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Role;
import com.xj.groupbuy.entity.RoleMenu;
import com.xj.groupbuy.mapper.RoleMapper;
import com.xj.groupbuy.mapper.RoleMenuMapper;
import com.xj.groupbuy.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    
    @Override
    public CommonVO deleteRole(Integer roleId) {
        
        Integer roleMenuCount = roleMenuMapper.selectCount(new QueryWrapper<RoleMenu>().eq("role_id", roleId));
        if(roleMenuCount != 0){
            return new CommonVO(false,"该角色下仍有某些菜单权限，请清除后再删除角色");
        } else {
            int i = roleMapper.deleteById(roleId);
            return new CommonVO(i == 1,i==1?"删除成功":"删除失败");
        }
    }
}

package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xj.groupbuy.common.util.NullUtils;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Role;
import com.xj.groupbuy.entity.RoleMenu;
import com.xj.groupbuy.mapper.RoleMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xj.groupbuy.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-24
 */
@Service
@Primary
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public CommonVO updateRoleMenu(Integer roleId, List<Integer> menuIds) {

//      menuIds:   3 4 8 9
//      已存在:    1 3 4 6
        // 1. 先根据roleId查询出对应的menuIds
        List<Integer> menuIdsByRoleId = roleMenuMapper.getMenuIdsByRoleId(roleId, false);

        if (!this.checkBaseMenu(roleId, menuIds)) {
            return new CommonVO(false, "该角色的 【角色相关菜单权限】 不可删除");
        }

        if (NullUtils.notEmpty(menuIds)) {
            // 3. 先删除不该存在的 id 先删除 1，3
            roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("role_id", roleId).notIn("menu_id", menuIds));

            // 4. 保存不存在的 id  8，9
            for (Integer menuId : menuIds) {
                if (!menuIdsByRoleId.contains(menuId)) {
                    RoleMenu roleMenu = new RoleMenu(roleId, menuId);
                    roleMenuMapper.insert(roleMenu);
                }
            }
        } else {
            // 将该角色的角色菜单全部删除
            roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("role_id", roleId));
        }

        return new CommonVO(true, "修改成功");
    }

    private boolean checkBaseMenu(Integer roleId, List<Integer> changeMenuIds) {
        // 管理员的某几项权限是不可被删除的
        // 在这里做校验，如果是管理员
        if (roleId == 1) {
            if (changeMenuIds == null)
                return false;
            
            return changeMenuIds.contains(7) && changeMenuIds.contains(8) && changeMenuIds.contains(10) && changeMenuIds.contains(2);
        }
        return true;
    }
}

package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xj.groupbuy.common.util.NullUtils;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Role;
import com.xj.groupbuy.entity.RoleMenu;
import com.xj.groupbuy.entity.UserRole;
import com.xj.groupbuy.mapper.UserMapper;
import com.xj.groupbuy.mapper.UserRoleMapper;
import com.xj.groupbuy.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-02
 */
@Service
@Primary
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public CommonVO getUserRoleById(String userId) {

        List<Integer> userRolesById = userRoleMapper.getUserRolesIdById(userId);
        return new CommonVO(true, userRolesById);
    }

    @Override
    public CommonVO updateUserRoleById(String userId, List<Integer> roleIds) {

        // 这边的处理和RoleMenu的 {如下} 类似
        //     public CommonVO updateRoleMenu(Integer roleId, List<Integer> menuIds) {


        List<Integer> userRoleIds = userRoleMapper.getUserRolesIdById(userId);

        if (!this.checkBaseRole(userId,roleIds)) {
            return new CommonVO(false,"该用户的 【管理员】 权限不可删除");
        }

        if (NullUtils.notEmpty(roleIds)) {
            // 3. 先删除不该存在的 id 先删除 1，3
            userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id",userId).notIn("role_id", roleIds));

            // 4. 保存不存在的 id  8，9
            for (Integer roleId : roleIds) {
                if (!userRoleIds.contains(roleId)) {
                    UserRole userRole = new UserRole(userId, roleId);
                    userRoleMapper.insert(userRole);
                }
            }
        } else {
            // 将该用户的所有角色全部删除
            userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id", userId));
        }

        return new CommonVO(true, "修改成功");
    }

    public boolean checkBaseRole(String userId, List<Integer> changeUserRoleIds) {
        
        // 如果是管理员账号
        if(userId.equals("1")){
            if(changeUserRoleIds == null){
                return false;
            }
            return changeUserRoleIds.contains(1); 
        }
        
        return true;
    }
}

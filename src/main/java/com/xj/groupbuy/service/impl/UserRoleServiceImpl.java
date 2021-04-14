package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xj.groupbuy.common.util.NullUtils;
import com.xj.groupbuy.common.util.OfficeUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Role;
import com.xj.groupbuy.entity.RoleMenu;
import com.xj.groupbuy.entity.StaffScore;
import com.xj.groupbuy.entity.UserRole;
import com.xj.groupbuy.mapper.RoleMapper;
import com.xj.groupbuy.mapper.StaffScoreMapper;
import com.xj.groupbuy.mapper.UserMapper;
import com.xj.groupbuy.mapper.UserRoleMapper;
import com.xj.groupbuy.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

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
    private StaffScoreMapper staffScoreMapper;
    @Autowired
    private RoleMapper roleMapper;


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

        if (!this.checkBaseRole(userId, roleIds)) {
            return new CommonVO(false, "该用户的 【管理员】 权限不可删除");
        }

        if (NullUtils.notEmpty(roleIds)) {
            // 3. 先删除不该存在的 id 先删除 1，3
            userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id", userId).notIn("role_id", roleIds));

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

    @Override
    public CommonVO addRoleBatch(MultipartFile[] files, String userRoleName) {
        
        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("name", userRoleName));

        List<String> exitsUserId = new ArrayList<>();
        
        for (MultipartFile file : files) {
            Map<Integer, List<Object>> result = OfficeUtil.readExcelContents(file);
            List<String> userIds = new ArrayList<>();
            result.forEach((k,v)->{
                if(v.get(0).toString().equals("userId")){
                    for (int i = 1; i < v.size(); i++) {
                        userIds.add(v.get(i).toString());
                    }
                }
            });
            System.out.println(userIds);

            for (String userId : userIds) {
                Integer count = userRoleMapper.selectCount(new QueryWrapper<UserRole>().eq("user_id", userId));
                if(count == 0){
                    UserRole userRole = new UserRole(userId, role.getRoleId());
                    userRoleMapper.insert(userRole);
                    
                    // 插入员工基础积分
                    staffScoreMapper.insert(new StaffScore(userId));
                    
                } else {
                    // 已存在的用户ID
                    exitsUserId.add(userId);
                }
            }
        }
        return new CommonVO(true, "上传成功",exitsUserId);
    }

    @Override
    public CommonVO addRoleSingle(String userId, String userRoleName) {
        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("name", userRoleName));
        UserRole userRole = new UserRole(userId, role.getRoleId());
        Integer count = userRoleMapper.selectCount(new QueryWrapper<UserRole>().eq("user_id", userId).eq("role_id", role.getRoleId()));
        if (count != 0) {
            return new CommonVO(false, "当前用户已有该身份");
        } else {
            int insert = userRoleMapper.insert(userRole);
            // 插入员工基础积分
            staffScoreMapper.insert(new StaffScore(userId));
            if (insert == 1) {
                return new CommonVO(true, "添加成功");
            } else {
                return new CommonVO(false, "添加异常，变更" + insert + "条记录");
            }
        }
    }

    @Override
    public CommonVO deleteRole(String userId, String userRoleName) {
        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("name", userRoleName));
        if(role == null){
            return new CommonVO(false,"无 [ "+userRoleName+" ] 身份");
        } else {
            int delete = userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id", userId).eq("role_id", role.getRoleId()));
            if (delete == 1) {
                return new CommonVO(true, "删除成功");
            } else {
                return new CommonVO(false, "删除异常，变更" + delete + "条记录");
            }
        }
    }

    @Override
    public boolean checkRole(String userId, String userRoleName) {
        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("name", userRoleName));
        Integer count = userRoleMapper.selectCount(new QueryWrapper<UserRole>().eq("user_id", userId).eq("role_id", role.getRoleId()));
        return count >= 1;
    }

    public boolean checkBaseRole(String userId, List<Integer> changeUserRoleIds) {

        // 如果是管理员账号
        if (userId.equals("1")) {
            if (changeUserRoleIds == null) {
                return false;
            }
            return changeUserRoleIds.contains(1);
        }

        return true;
    }
}

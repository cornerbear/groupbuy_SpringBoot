package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.util.OfficeUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Role;
import com.xj.groupbuy.entity.User;
import com.xj.groupbuy.entity.UserRole;
import com.xj.groupbuy.mapper.RoleMapper;
import com.xj.groupbuy.mapper.UserMapper;
import com.xj.groupbuy.mapper.UserRoleMapper;
import com.xj.groupbuy.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-07
 */
@Service
@Primary
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 用户登录时设置权限
        user.setRoles(userMapper.getUserRolesById(user.getUserId()));
        return user;
    }

    @Override
    public int updatePassword(String userId, String newPassword) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("password", newPassword);
        updateWrapper.eq("user_id", userId);
        int update = userMapper.update(null, updateWrapper);
        return update;

    }

    @Override
    public int checkUser(String username) {
        return userMapper.selectCount(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public IPage<User> userRoleTable(String userId, String name, Integer pageNo, Integer pageSize) {
        Page<User> userPage = new Page<>(pageNo, pageSize);
        return userMapper.getAllUserSimple(name, userId, userPage);
    }

    @Override
    public CommonVO getUserAndRoleById(String userId) {
        User user = userMapper.selectById(userId);
        user.setRoles(userMapper.getUserRolesById(userId));
        return new CommonVO(true, user);
    }

    @Override
    public IPage<?> getUsersByRole(String userRole, Integer pageNo, Integer pageSize) {
        Page<User> userPage = new Page<>(pageNo, pageSize);
        return userMapper.getUsersByRole(userRole, userPage);
    }

}

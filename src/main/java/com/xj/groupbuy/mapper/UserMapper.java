package com.xj.groupbuy.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.entity.Role;
import com.xj.groupbuy.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Role> getUserRolesById(String userId);

    IPage<User> getAllUserSimple(String name, String userId,
                                              Page<?> page);

    IPage<?> getUsersByRole(String userRole, Page<User> userPage);

}

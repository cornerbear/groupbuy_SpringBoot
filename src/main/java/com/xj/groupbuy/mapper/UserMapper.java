package com.xj.groupbuy.mapper;

import com.xj.groupbuy.entity.Role;
import com.xj.groupbuy.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
}

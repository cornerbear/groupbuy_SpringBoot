package com.xj.groupbuy.service;

import com.xj.groupbuy.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-07
 */
public interface IUserService extends IService<User>, UserDetailsService {

    int updatePassword(String userId,String newPassword);

    int checkUser(String username);
}

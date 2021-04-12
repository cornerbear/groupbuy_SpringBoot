package com.xj.groupbuy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

    IPage<User> userRoleTable(String userId, String name, Integer pageNo, Integer pageSize);

    CommonVO getUserAndRoleById(String userId);

    IPage<?> getUsersByRole(String userRole, Integer pageNo, Integer pageSize);

}

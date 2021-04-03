package com.xj.groupbuy.service;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-02
 */
public interface IUserRoleService extends IService<UserRole> {

    CommonVO getUserRoleById(String userId);

    CommonVO updateUserRoleById(String userId, List<Integer> roleIds);
}

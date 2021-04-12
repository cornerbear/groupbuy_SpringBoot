package com.xj.groupbuy.service;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

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

    CommonVO addRoleBatch(MultipartFile[] files, String userRoleName);

    CommonVO addRoleSingle(String userId, String userRoleName);

    CommonVO deleteRole(String userId, String userRoleName);

    boolean checkRole(String userId, String userRoleName);
}

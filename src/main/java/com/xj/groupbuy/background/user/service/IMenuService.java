package com.xj.groupbuy.background.user.service;

import com.xj.groupbuy.background.user.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-11
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getAllMenusWithRole();
}

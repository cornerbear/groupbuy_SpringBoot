package com.xj.groupbuy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.entity.Menu;
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

    List<Menu> getMenusByUserId();

    List<Menu> getAllMenusIdAndName();

    List<Integer> getLeafMenuIdsByRoleId(Integer roleId);

    IPage<?> menuTable(String parentId, Integer pageNo, Integer pageSize);

    List<Menu> menuTree();

    boolean saveMenu(Menu menu);

    CommonVO deleteMenu(Integer id);

    List<Menu> getMenuEasyTree();
}

package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.util.TreeUtil;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.entity.Category;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.entity.Menu;
import com.xj.groupbuy.mapper.MenuMapper;
import com.xj.groupbuy.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-11
 */
@Service
@Primary
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    
    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    @Override
    public List<Menu> getMenusByUserId() {
        List<Menu> menusByUserId = menuMapper.getMenusByUserId(UserUtil.getCurrentUser().getUserId());
        
        List<Menu> res = new CopyOnWriteArrayList<>();
        List<Menu> sons = new CopyOnWriteArrayList<>();
        for (Menu menu : menusByUserId) {
            if (menu.getParentId().equals("0")) {
                res.add(menu);
            } else {
                sons.add(menu);
            }
        }
        for (Menu parent : res) {
            recursionMenu(parent,sons);
        }
        
        return res;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    @Override
    public List<Integer> getMenuIdsByRoleId(Integer roleId) {
        return menuMapper.getMenuIdsByRoleId(roleId);
    }

    @Override
    public IPage<?> menuTable(String parentId, Integer pageNo, Integer pageSize) {

        Page<Menu> paramPage = new Page<>(pageNo,pageSize);
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        if(parentId != null){
            wrapper.eq("parentId",parentId);
        }
        return this.page(paramPage, wrapper);
    }

    @Override
    public List<Menu> menuTree() {

        List<Menu> menus = menuMapper.selectList(null);
        List<Menu> treeList = TreeUtil.getTreeList("0", menus);
        return treeList;
    }

    @Override
    @Transactional
    public boolean saveMenu(Menu menu) {
        return this.save(menu);
    }

    private void recursionMenu(Menu parent, List<Menu> sons) {
        if(sons.size() == 0) return ;
        String menuId = parent.getId();
        for (Menu son : sons) {
            if (son.getParentId().equals(menuId)) {
                if(parent.getChildren() == null)
                    parent.setChildren(new CopyOnWriteArrayList<>());
                parent.getChildren().add(son);
                sons.remove(son);
                recursionMenu(son,sons);
            }
        }
    }
}

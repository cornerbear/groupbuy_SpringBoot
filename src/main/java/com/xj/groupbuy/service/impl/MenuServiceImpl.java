package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.util.TreeUtil;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Category;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.entity.Menu;
import com.xj.groupbuy.entity.RoleMenu;
import com.xj.groupbuy.mapper.MenuMapper;
import com.xj.groupbuy.mapper.RoleMenuMapper;
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
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    
    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    @Override
    public List<Menu> getMenusByUserId() {
        List<Menu> menusByUserId = menuMapper.getMenusByUserId(UserUtil.getCurrentUser().getUserId());

        return TreeUtil.getTreeList(0, menusByUserId);

    }

    @Override
    public List<Menu> getAllMenusIdAndName() {
        return menuMapper.getAllMenusIdAndName();
    }

    @Override
    public List<Integer> getLeafMenuIdsByRoleId(Integer roleId) {
        return roleMenuMapper.getMenuIdsByRoleId(roleId,true);
    }

    @Override
    public IPage<?> menuTable(String parentId, Integer pageNo, Integer pageSize) {

        Page<Menu> paramPage = new Page<>(pageNo,pageSize);
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        if(parentId != null){
            wrapper.eq("parent_id",parentId)
            .or()
            .eq("id",parentId);
        }
        return this.page(paramPage, wrapper);
    }

    @Override
    public List<Menu> menuTree() {

        List<Menu> menus = menuMapper.selectList(null);
        List<Menu> treeList = TreeUtil.getTreeList(0, menus);
        return treeList;
    }

    @Override
    public boolean saveMenu(Menu menu) {
        boolean save = this.save(menu);
        Integer id = menu.getId();
        RoleMenu roleMenu = new RoleMenu(1,id);
        roleMenuMapper.insert(roleMenu);
        return save;
    }

    @Override
    public CommonVO deleteMenu(Integer id) {
        Integer sonCount = menuMapper.selectCount(new QueryWrapper<Menu>().eq("parent_id", id));
        if(sonCount > 0){
            return new CommonVO(false,"尚存在子菜单，禁止删除");
        } else {
            menuMapper.deleteById(id);
            roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("menu_id",id));
            return new CommonVO(true,"删除成功");
        }
    }

    @Override
    public List<Menu> getMenuEasyTree() {
        List<Menu> menus = menuMapper.selectList(new QueryWrapper<Menu>().select("id","name").orderByAsc("id"));
        List<Menu> treeList = TreeUtil.getTreeList(0, menus);
        return treeList;
    }

}

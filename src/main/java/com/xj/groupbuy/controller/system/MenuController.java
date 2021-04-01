package com.xj.groupbuy.controller.system;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.entity.Menu;
import com.xj.groupbuy.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-11
 */
@RestController
@RequestMapping("system/menu")
public class MenuController {


    @Autowired
    IMenuService menuService;

    /**
     * 根据id删除菜单
     */
    @DeleteMapping
    public CommonVO deleteMenu(Integer id){
        return menuService.deleteMenu(id);
    }
    
    /**
     * 保存菜单
     */
    @PostMapping
    public CommonVO saveMenu(@RequestBody Menu menu){
        boolean save = menuService.saveMenu(menu);
        return new CommonVO(save,save?"保存成功":"保存失败");
    }

    /**
     * 根据id查询单个菜单
     */
    @GetMapping("{id}")
    public CommonVO getMenu(@PathVariable Integer id){
        return new CommonVO(true,menuService.getById(id));  
    }

    /**
     * 更新菜单
     */
    @PutMapping
    public CommonVO updateMenu(@RequestBody Menu menu){
        if(menu.getParentId() == null){
            menu.setParentId(0);
        }
        boolean update = menuService.updateById(menu);
        return new CommonVO(update,update?"修改成功":"修改失败");
    }

    /**
     * 系统初始化左侧菜单
     * @return 树形菜单
     */
    @GetMapping("initLeftMenu")
    public List<Menu> getMenusByUserId() {
        return menuService.getMenusByUserId();
    }

    /**
     * 获取所有菜单的id和name 
     * @return 用于菜单权限管理
     */
    @GetMapping("IdAndName")
    public List<Menu> getAllMenusIdAndName() {
        return menuService.getAllMenusIdAndName();
    }

    /**
     * 获取菜单表格
     * @return 用于菜单管理的表格展示
     */
    @GetMapping("table")
    public CommonVO menuTable(String parentId, Integer pageNo, Integer pageSize, HttpServletRequest request){
        return new CommonVO(true,menuService.menuTable(parentId,pageNo,pageSize));
    }

    /**
     * 获取菜单树
     * @return 用于菜单管理的树状展示
     */
    @GetMapping("tree")
    public CommonVO menuTree(){
        return new CommonVO(true,menuService.menuTree());
    }
    
    /**
     * 获取简单菜单树
     * @return 用于菜单管理的树状展示
     */
    @GetMapping("easyTree")
    public CommonVO menuEasyTree(){
        return new CommonVO(true,menuService.getMenuEasyTree());
    }

    /**
     * 获取某个角色所对应的菜单 ID
     * @return 用于菜单权限管理的树状选择
     */
    @GetMapping("ids/{roleId}")
    public List<Integer> getMenuIdsByRoleId(@PathVariable Integer roleId) {
        return menuService.getLeafMenuIdsByRoleId(roleId);
    }

}

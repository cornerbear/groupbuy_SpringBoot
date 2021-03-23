package com.xj.groupbuy.controller.system;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.entity.Menu;
import com.xj.groupbuy.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("system")
public class MenuController {


    @Autowired
    IMenuService menuService;
    
    @PostMapping("menu")
    public CommonVO saveMenu(@RequestBody Menu menu){
        boolean save = menuService.saveMenu(menu);
        return new CommonVO(save,save?"保存成功":"保存失败");
    }
    
    @GetMapping("menu")
    public List<Menu> getMenusByUserId() {
        return menuService.getMenusByUserId();
    }

    @GetMapping("menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }
    
    @GetMapping("menuTable")
    public CommonVO menuTable(String parentId,Integer pageNo,Integer pageSize){
        return new CommonVO(true,menuService.menuTable(parentId,pageNo,pageSize));
    }

    @GetMapping("menuTree")
    public CommonVO menuTree(){
        return new CommonVO(true,menuService.menuTree());
    }
    
    @GetMapping("menuIds/{roleId}")
    public List<Integer> getMenuIdsByRoleId(@PathVariable Integer roleId) {
        return menuService.getMenuIdsByRoleId(roleId);
    }

}

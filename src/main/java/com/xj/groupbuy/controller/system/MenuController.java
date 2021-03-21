package com.xj.groupbuy.controller.system;


import com.xj.groupbuy.entity.Menu;
import com.xj.groupbuy.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping("menu")
    public List<Menu> getMenusByHrId() {
        return menuService.getMenusByUserId();
    }

}

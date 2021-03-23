package com.xj.groupbuy.controller.system;


import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Role;
import com.xj.groupbuy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleController {
    
    @Autowired
    private IRoleService roleService;

    @RequestMapping("role/all")
    public CommonVO getAllRoles(){
        List<Role> list = roleService.list();
        return new CommonVO(true,list);
    }
}

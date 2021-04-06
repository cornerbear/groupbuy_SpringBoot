package com.xj.groupbuy.controller.admin;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.User;
import com.xj.groupbuy.service.IUserRoleService;
import com.xj.groupbuy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/5
 */
@RestController
@RequestMapping("admin/user")
public class ManageUserController {
    
    @Autowired
    private IUserService userService;
    
    @GetMapping("{userId}")
    public CommonVO getUser(@PathVariable String userId){
        return userService.getUserAndRoleById(userId);
    }
    
    @PutMapping
    public CommonVO updateUser(@RequestBody User user){
        boolean b = userService.updateById(user);
        return new CommonVO(b,b?"修改成功":"修改失败");
    }
}

package com.xj.groupbuy.controller.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xj.groupbuy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/11
 */

@RestController
@RequestMapping("manager/staff")
public class StaffController {
    
    @Autowired
    private IUserService userService;

    @GetMapping("all/{pageNo}/{pageSize}")
    @ResponseBody
    public IPage<?> getAllStaff(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize) {
        return userService.getUsersByRole("ROLE_STAFF",pageNo,pageSize);
    }
    
}

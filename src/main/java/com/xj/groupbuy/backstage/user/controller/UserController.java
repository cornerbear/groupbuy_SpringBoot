package com.xj.groupbuy.backstage.user.controller;


import com.xj.groupbuy.backstage.user.entity.User;
import com.xj.groupbuy.backstage.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-06
 */
@RestController
@RequestMapping("admin")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private IUserService userService;
    
    @GetMapping("user/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") String id){
        User user = userService.getById(id);
        return user;
    }
}

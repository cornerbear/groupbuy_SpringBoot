package com.xj.groupbuy.controller.admin;


import com.xj.groupbuy.entity.User;
import com.xj.groupbuy.service.IUserService;
import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-07
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;
    
    @RequestMapping("register")
    public CommonVO userRegister(String username, String password){
        //如果用户名存在，返回错误
        if (userService.loadUserByUsername(username) != null) {
            return new CommonVO(false,"用户名已存在");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        boolean save = userService.save(new User(username, password));
        return new CommonVO(save,save?"注册成功":"注册失败");
    }
}

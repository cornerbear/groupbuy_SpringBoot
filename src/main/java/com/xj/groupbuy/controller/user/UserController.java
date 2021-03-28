package com.xj.groupbuy.controller.user;

import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.User;
import com.xj.groupbuy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/28
 */
@RestController
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private IUserService userService;
    
    @GetMapping
    public CommonVO getUserDetail(){
        User currentUser = UserUtil.getCurrentUser();
        User user = userService.getById(currentUser.getUserId());
        return new CommonVO(true,user);
    }
    @PutMapping
    public CommonVO updateUserDetail(@RequestBody User user){
        
        User currentUser = UserUtil.getCurrentUser();
        
        if(!user.getUserId().equals(currentUser.getUserId())){
            return new CommonVO(false,"异常操作");
        } else {
            boolean update = userService.updateById(user);
            return new CommonVO(update,update?"更新成功":"更新失败");
        }
    }
    
}

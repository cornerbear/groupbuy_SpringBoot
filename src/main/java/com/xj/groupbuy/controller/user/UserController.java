package com.xj.groupbuy.controller.user;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.User;
import com.xj.groupbuy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/28
 */
@RestController
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private IUserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @GetMapping
    public CommonVO getUserDetail(){
        User currentUser = UserUtil.getCurrentUser();
        User user = userService.getById(currentUser.getUserId());
        user.setPassword(null);
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
    @PutMapping("updatePassword")
    public CommonVO updatePassword(@RequestBody Map<String, Object> map){
        String oldPassword = map.get("oldPassword").toString();
        String newPassword = map.get("newPassword").toString();
        String newPassword1 = map.get("newPassword1").toString();

        User currentUser = UserUtil.getCurrentUser();
        if(currentUser.getPassword().equals(passwordEncoder.encode(oldPassword))){
            if(newPassword.equals(newPassword1)){
                int i = userService.updatePassword(currentUser.getUserId(), passwordEncoder.encode(newPassword));
                return new CommonVO(i == 1,i == 1?"修改成功":"修改失败"+i);
            } else {
                return new CommonVO(false,"两次输入密码不一样");
            }
        } else {
            return new CommonVO(false,"旧密码不正确");
        }
    }
    
}

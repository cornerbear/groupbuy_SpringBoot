package com.xj.groupbuy.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.User;
import com.xj.groupbuy.service.IUserRoleService;
import com.xj.groupbuy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-02
 */
@RestController
@RequestMapping("/admin/userRole")
public class UserRoleController {
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    
    @PutMapping
    public CommonVO updateUserRoleById(@RequestParam(value="userId") String userId,@RequestParam(value="roleIds") List<Integer> roleIds){
        return userRoleService.updateUserRoleById(userId,roleIds);
    }
    
    @GetMapping("{userId}")
    public CommonVO getUserRoleById(@PathVariable String userId){
        return userRoleService.getUserRoleById(userId);
    }
    
    @GetMapping("all/{pageNo}/{pageSize}")
    public IPage<User> getUserRoleTable(String userId, String name, @PathVariable Integer pageNo, @PathVariable Integer pageSize){
        return userService.userRoleTable(userId,name,pageNo,pageSize);
    }

}

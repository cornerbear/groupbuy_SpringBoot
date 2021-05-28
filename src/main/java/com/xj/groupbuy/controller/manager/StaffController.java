package com.xj.groupbuy.controller.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.service.IUserRoleService;
import com.xj.groupbuy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/11
 */

@RestController
@RequestMapping("manager/grouper")
public class StaffController {
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping("all/{pageNo}/{pageSize}")
    public IPage<?> getAllStaff(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize) {
        return userService.getUsersByRole("ROLE_GROUPER",pageNo,pageSize);
    }
    @DeleteMapping("{userId}")
    public CommonVO deleteStaff(@PathVariable String userId){
        return userRoleService.deleteRole(userId,"ROLE_GROUPER");
    }

    @PostMapping("batchAdd")
    public CommonVO addStaffBatch(@RequestParam("files") MultipartFile[] files){
        try {
            return userRoleService.addRoleBatch(files,"ROLE_GROUPER");
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonVO(false,"文件上传失败，请检查格式");
        }
    }

    @PostMapping("singleAdd/{userId}")
    public CommonVO addStaffSingle(@PathVariable String userId){
        try {
            return userRoleService.addRoleSingle(userId,"ROLE_GROUPER");
            
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonVO(false,"文件上传失败，请检查格式");
        }
    }
    
}

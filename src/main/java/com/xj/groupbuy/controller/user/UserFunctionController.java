package com.xj.groupbuy.controller.user;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.service.IRegionService;
import com.xj.groupbuy.service.IUserApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/20
 */
@RestController
@RequestMapping("user")
public class UserFunctionController {

    @Autowired
    private IUserApplyService userApplyService;
    
    @Autowired
    private IRegionService regionService;
    
    @GetMapping("community/{streetCode}")
    public CommonVO getCommunityByStreetCode(@PathVariable String streetCode){
        return new CommonVO(true,regionService.getCommunityByStreetCode(streetCode)); 
    }
    
    @PostMapping("apply/grouper")
    public CommonVO applyGrouper(){
        return userApplyService.apply("grouper");
    }
}

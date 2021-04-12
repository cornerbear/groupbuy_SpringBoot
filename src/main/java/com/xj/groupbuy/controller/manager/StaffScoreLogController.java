package com.xj.groupbuy.controller.manager;


import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.service.IStaffScoreLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-12
 */
@RestController
@RequestMapping("manager/staffScoreLog")
public class StaffScoreLogController {

    @Autowired
    private IStaffScoreLogService staffScoreLogService;

    @GetMapping("{userId}")
    public CommonVO getStaffScoreLogs(@PathVariable String userId){

        return staffScoreLogService.getStaffScoreLogs(userId);
    }
}

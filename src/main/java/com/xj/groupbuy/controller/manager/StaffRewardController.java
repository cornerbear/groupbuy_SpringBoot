package com.xj.groupbuy.controller.manager;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.common.wrapper.MyQueryWrapper;
import com.xj.groupbuy.entity.StaffReward;
import com.xj.groupbuy.entity.StaffScoreLog;
import com.xj.groupbuy.service.IStaffRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-14
 */
@RestController
@RequestMapping("manager/staffReward")
public class StaffRewardController {

    @Autowired
    private IStaffRewardService staffRewardService;


    @GetMapping("all/{pageNo}/{pageSize}")
    public IPage<?> getAllStaffReward(@RequestParam(required = false) String userId, @PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize) {

        Page<StaffReward> page = new Page<>(pageNo, pageSize);
        
        return staffRewardService.page(page, new MyQueryWrapper<StaffReward>().eq("user_id", userId));
    }

    @GetMapping("{id}")
    public CommonVO getStaffReward(@PathVariable Integer id){
        StaffReward byId = staffRewardService.getById(id);
        return new CommonVO(true,byId);
    }
    @PostMapping
    public CommonVO saveStaffReward(@RequestBody StaffReward staffReward) {
        return staffRewardService.saveStaffReward(staffReward);
    }
    @PutMapping
    public CommonVO updateStaffReward(@RequestBody StaffReward staffReward){
        boolean b = staffRewardService.updateById(staffReward);
        return new CommonVO(b,b?"修改成功":"修改失败");
    }
    @DeleteMapping("{id}")
    public CommonVO deleteStaffReward(@PathVariable Integer id){
        boolean b = staffRewardService.removeById(id);
        return new CommonVO(b,b?"删除成功":"删除失败");
    }
}

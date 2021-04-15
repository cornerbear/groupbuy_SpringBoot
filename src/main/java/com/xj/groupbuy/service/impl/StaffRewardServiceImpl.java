package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.util.DateUtil;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.StaffReward;
import com.xj.groupbuy.mapper.StaffRewardMapper;
import com.xj.groupbuy.service.IStaffRewardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-14
 */
@Service
@Primary
public class StaffRewardServiceImpl extends ServiceImpl<StaffRewardMapper, StaffReward> implements IStaffRewardService {

    @Autowired
    private StaffRewardMapper staffRewardMapper;

    @Override
    public CommonVO saveStaffReward(StaffReward staffReward) {
        staffReward.setActionUserId(UserUtil.getUserId());
        staffReward.setCreateTime(DateUtil.getCurrentDate());
        int insert = staffRewardMapper.insert(staffReward);
        return new CommonVO(insert==1,insert==1?"发放成功":"发放失败");
    }
}

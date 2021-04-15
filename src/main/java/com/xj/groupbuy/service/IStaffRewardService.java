package com.xj.groupbuy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.StaffReward;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-14
 */
public interface IStaffRewardService extends IService<StaffReward> {

    CommonVO saveStaffReward(StaffReward staffReward);
}

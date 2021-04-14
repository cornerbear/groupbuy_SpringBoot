package com.xj.groupbuy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.StaffScore;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-12
 */
public interface IStaffScoreService extends IService<StaffScore> {

    CommonVO gotStaffScore(String userId);

    IPage<?> getStaffScores(Integer pageNo, Integer pageSize);
}

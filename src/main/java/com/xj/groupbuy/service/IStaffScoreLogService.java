package com.xj.groupbuy.service;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.StaffScoreLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-12
 */
public interface IStaffScoreLogService extends IService<StaffScoreLog> {

    CommonVO getStaffScoreLogs(String userId);

    CommonVO insertStaffScoreLog(StaffScoreLog staffScoreLog);
}

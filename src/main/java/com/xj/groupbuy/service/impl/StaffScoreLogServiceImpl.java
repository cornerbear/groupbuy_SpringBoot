package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xj.groupbuy.common.util.DateUtil;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.StaffScore;
import com.xj.groupbuy.entity.StaffScoreLog;
import com.xj.groupbuy.mapper.StaffScoreLogMapper;
import com.xj.groupbuy.mapper.StaffScoreMapper;
import com.xj.groupbuy.service.IStaffScoreLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-12
 */
@Service
public class StaffScoreLogServiceImpl extends ServiceImpl<StaffScoreLogMapper, StaffScoreLog> implements IStaffScoreLogService {

    @Autowired
    private StaffScoreLogMapper staffScoreLogMapper;
    @Autowired
    private StaffScoreMapper staffScoreMapper;
    
    @Override
    public CommonVO getStaffScoreLogs(String userId) {

        List<StaffScoreLog> logs = staffScoreLogMapper.selectList(new QueryWrapper<StaffScoreLog>().eq("user_id", userId));
        return new CommonVO(true,logs);
    }

    @Override
    public CommonVO insertStaffScoreLog(StaffScoreLog staffScoreLog) {

        String userId = staffScoreLog.getUserId();
        StaffScore staffScore = staffScoreMapper.selectOne(new QueryWrapper<StaffScore>().eq("user_id", userId));
        staffScoreLog.setBeforeScore(staffScore.getScore());
        BigDecimal afterScore;
        if(staffScoreLog.getAction()){
            // 增加
            afterScore = staffScore.getScore().add(staffScoreLog.getChangeScore());
        } else {
            // 减少
            afterScore = staffScore.getScore().subtract(staffScoreLog.getChangeScore());
        }

        staffScoreLog.setAfterScore(afterScore);
        staffScoreLog.setLogTime(DateUtil.getCurrentDate());
        staffScoreLog.setActionUserId(UserUtil.getCurrentUser().getUserId());

        staffScore.setScore(afterScore);
        
        staffScoreLogMapper.insert(staffScoreLog);
        staffScoreMapper.updateById(staffScore);
        
        return new CommonVO(true,"变更成功");
    }
}

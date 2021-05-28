package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.StaffScore;
import com.xj.groupbuy.entity.User;
import com.xj.groupbuy.mapper.StaffScoreMapper;
import com.xj.groupbuy.service.IStaffScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xj.groupbuy.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-12
 */
@Service
public class StaffScoreServiceImpl extends ServiceImpl<StaffScoreMapper, StaffScore> implements IStaffScoreService {

    @Autowired
    private StaffScoreMapper staffScoreMapper;
    @Autowired
    private IUserRoleService userRoleService;
    
    // 为了摆脱事务的影响，所以用got
    @Override
    public CommonVO gotStaffScore(String userId) {
        boolean isStaff = userRoleService.checkRole(userId,"ROLE_GROUPER");
        if(isStaff){
            StaffScore staffScore = staffScoreMapper.selectOne(new QueryWrapper<StaffScore>().eq("user_id", userId));
            if(staffScore == null){
                staffScore = new StaffScore(userId);
                staffScoreMapper.insert(staffScore);
            }
            return new CommonVO(true,staffScore);
        } else {
            return new CommonVO(false,"该用户不为员工");
        }
    }

    @Override
    public IPage<?> getStaffScores(Integer pageNo, Integer pageSize) {
        Page<?> page = new Page<>(pageNo, pageSize);
        return staffScoreMapper.getStaffScores(page);
    }
}

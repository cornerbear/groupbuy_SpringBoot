package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.UserApply;
import com.xj.groupbuy.mapper.UserApplyMapper;
import com.xj.groupbuy.service.IUserApplyService;
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
 * @since 2021-04-20
 */
@Service
@Primary
public class UserApplyServiceImpl extends ServiceImpl<UserApplyMapper, UserApply> implements IUserApplyService {

    @Autowired
    private UserApplyMapper userApplyMapper;
    
    @Override
    public CommonVO apply(String grouper) {
        UserApply userApply = new UserApply(UserUtil.getUserId(), grouper);
        int insert = userApplyMapper.insert(userApply);
        return new CommonVO(insert==1,insert==1?"申请成功":"申请失败");
    }
}

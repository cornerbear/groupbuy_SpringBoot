package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.entity.Community;
import com.xj.groupbuy.mapper.CommunityMapper;
import com.xj.groupbuy.service.ICommunityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-15
 */
@Service
@Primary
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements ICommunityService {

}

package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.entity.Community;
import com.xj.groupbuy.mapper.CommunityMapper;
import com.xj.groupbuy.service.ICommunityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Autowired
    private CommunityMapper communityMapper;
    
    @Override
    public IPage<?> table(String level, String parentCode, Integer pageNo, Integer pageSize) {

        Page<Map<String,Object>> page = new Page<>(pageNo,pageSize);

        if("province".equals(level)){
            return communityMapper.getTable(parentCode,null,null,null,page);
        } else if("city".equals(level)){
            return communityMapper.getTable(null,parentCode,null,null,page);
        } else if("area".equals(level)){
            return communityMapper.getTable(null,null,parentCode,null,page);
        } else if("street".equals(level)){
            return communityMapper.getTable(null,null,null,parentCode,page);
        } else {
            return communityMapper.getTable(null,null,null,null,page);
        }
    }
}

package com.xj.groupbuy.backstage.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xj.groupbuy.backstage.test.bean.TTest;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/1
 */
//@Repository
@Mapper
public interface TestMapper extends BaseMapper<TTest> {
    
    
    TTest getTest(String id);
}

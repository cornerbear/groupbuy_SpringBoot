package com.xj.groupbuy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xj.groupbuy.bean.TTest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/1
 */
//@Repository
@Mapper
public interface TestMapper extends BaseMapper<TTest> {
    
    
    TTest getTest(String id);
}

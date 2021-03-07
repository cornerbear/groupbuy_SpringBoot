package com.xj.groupbuy.background.test.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xj.groupbuy.background.test.bean.TTest;
import com.xj.groupbuy.background.test.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/1
 */
@Service
public class TestService extends ServiceImpl<TestMapper,TTest> {
    
    @Autowired
    TestMapper testMapper;
    
    public TTest getTestById(String id){
        TTest test = testMapper.getTest(id);
        return test;
    }
}

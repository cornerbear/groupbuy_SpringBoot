package com.xj.groupbuy.background.test.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.background.test.bean.TTest;
import com.xj.groupbuy.background.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/1
 */
@Controller
public class TestController {
    
    @Autowired
    private TestService testService;
    
    @GetMapping("test/{id}")
    @ResponseBody
    public TTest getTestById(@PathVariable String id){
        TTest testById = testService.getTestById(id);
        return testById;
    }

    @GetMapping("tests/{pageNo}/{pageSize}")
    @ResponseBody
    public Page getTestS(@PathVariable(value = "pageNo") Integer pageNo,@PathVariable(value = "pageSize") Integer pageSize){
        
        // 分页查询数据 第几页，每页几个
        Page<TTest> testPage = new Page<>(pageNo,pageSize);
        Page<TTest> page = testService.page(testPage, null);
        return page;
    }
}

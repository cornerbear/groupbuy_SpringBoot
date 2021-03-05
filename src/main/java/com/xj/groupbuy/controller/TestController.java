package com.xj.groupbuy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.bean.TTest;
import com.xj.groupbuy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/1
 */
@Controller
public class TestController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private TestService testService;
    
    @GetMapping("test/{id}")
    @ResponseBody
    public TTest getTestById(@PathVariable String id){
        TTest testById = testService.getTestById(id);
        return testById;
    }

    @GetMapping("tests")
    @ResponseBody
    public Page getTestS(@RequestParam(value = "pn",defaultValue = "1") Integer pn){

        // 分页查询数据 第几页，每页几个
        Page<TTest> testPage = new Page<>(pn,2);
        Page<TTest> page = testService.page(testPage, null);
        return page;
    }
}

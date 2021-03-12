package com.xj.groupbuy.foreground.index.controller;

import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/11
 */
@RestController
public class ForegroundIndexController {
    
    @RequestMapping("/")
    public CommonVO foregroundIndex(){
        return new CommonVO(true,"前台首页");
    }
}

package com.xj.groupbuy.background.index.Controller;

import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/11
 */
@RestController
@RequestMapping("admin")
public class BackgroundIndexController {
    
    @RequestMapping("")
    public CommonVO backgroundIndex(){
        return new CommonVO(true,"管理员首页");
    }
}

package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.User;
import com.xj.groupbuy.mapper.LoginMapper;
import com.xj.groupbuy.mapper.UserMapper;
import com.xj.groupbuy.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/26
 */
@Service
@Primary
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RememberMeServices rememberMeServices;

    @Override
    public CommonVO checkLogin(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return new CommonVO(false, "");
        }
        Authentication authentication = rememberMeServices.autoLogin(request, response);
        if (authentication != null) {
            User user = (User) authentication.getPrincipal();
            user.setPassword(null);
            return new CommonVO(true, authentication.getPrincipal());
        }
        return new CommonVO(false, "");
    }
}

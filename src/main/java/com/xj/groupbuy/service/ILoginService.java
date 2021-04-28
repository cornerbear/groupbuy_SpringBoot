package com.xj.groupbuy.service;

import com.xj.groupbuy.common.vo.CommonVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/26
 */
public interface ILoginService {
    CommonVO checkLogin(HttpServletRequest request,HttpServletResponse response);
}

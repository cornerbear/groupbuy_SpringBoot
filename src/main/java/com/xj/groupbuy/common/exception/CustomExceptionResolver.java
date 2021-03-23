package com.xj.groupbuy.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/11
 */
@Component
@Slf4j
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) {
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        Map<String, Object> map = new HashMap<>();
        map.put("status", "error");
        map.put("exception", e);
        log.info("CustomExceptionResolver捕获到异常",e);
        if (e instanceof DataIntegrityViolationException) {
            map.put("msg", "该角色尚有关联的资源或用户，删除失败!");
        }
        mv.addAllObjects(map);
        return mv;
    }
}
package com.xj.groupbuy.common.security;

import com.xj.groupbuy.background.user.entity.Menu;
import com.xj.groupbuy.background.user.entity.Role;
import com.xj.groupbuy.background.user.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.security.access.SecurityConfig;

import java.util.Collection;
import java.util.List;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/11
 * 该类的主要功能就是通过当前的请求地址，获取该地址需要的用户角色
 */
@Component
<<<<<<< Updated upstream:src/main/java/com/xj/groupbuy/common/security/UrlFilterInvocationSecurityMetadataSource.java
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
=======
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    IMenuService menuService;
>>>>>>> Stashed changes:src/main/java/com/xj/groupbuy/common/security/CustomFilterInvocationSecurityMetadataSource.java
    
    @Autowired
    private IMenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
<<<<<<< Updated upstream:src/main/java/com/xj/groupbuy/common/security/UrlFilterInvocationSecurityMetadataSource.java
        if ("/login_p".equals(requestUrl)) {
            return null;
        }
        List<Menu> allMenu = menuService.list();
        for (Menu menu : allMenu) {
            if (antPathMatcher.match(menu.getMenuUrl(), requestUrl)&&menu.getRoles().size()>0) {
=======
//        List<Menu> menus = menuService.list();
        List<Menu> menus = menuService.getAllMenusWithRole();
        for (Menu menu : menus) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
>>>>>>> Stashed changes:src/main/java/com/xj/groupbuy/common/security/CustomFilterInvocationSecurityMetadataSource.java
                List<Role> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(values);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}

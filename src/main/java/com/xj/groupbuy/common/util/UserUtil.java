package com.xj.groupbuy.common.util;

import com.xj.groupbuy.background.user.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/11
 */
public class UserUtil {

    public static User getCurrentUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}

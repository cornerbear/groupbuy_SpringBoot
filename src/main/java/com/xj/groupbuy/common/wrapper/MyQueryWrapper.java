package com.xj.groupbuy.common.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.xj.groupbuy.common.util.NullUtils;


/**
 * Author : zhangxiaojian
 * Date : 2021/4/15
 */
public class MyQueryWrapper<T> extends QueryWrapper<T> {

    private static final long serialVersionUID = 1L;

    @Override
    protected QueryWrapper<T> addCondition(boolean condition, String column, SqlKeyword sqlKeyword, Object val) {
        if(NullUtils.isEmpty(val)){
            condition = false;
        }
        return super.addCondition(condition, column, sqlKeyword, val);
    }
}

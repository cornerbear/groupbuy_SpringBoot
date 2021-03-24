package com.xj.groupbuy.entity;

import java.util.List;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/23
 */
public interface TreeEntity<E> {
    Integer getTreeId();
    Integer getTreeParentId();
    default boolean isTreeEnabled(){
        return true;
    }
    void setTreeChildren(List<E> children);
}

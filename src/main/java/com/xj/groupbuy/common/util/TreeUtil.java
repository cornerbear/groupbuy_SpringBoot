package com.xj.groupbuy.common.util;

import com.xj.groupbuy.entity.TreeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/23
 */
public class TreeUtil{

    public static <E extends TreeEntity<E>> List<E> getTreeList(Integer topId, List<E> entityList) {
        List<E> resultList=new ArrayList<>();

        //获取顶层元素集合
        Integer parentId;
        for (E entity : entityList) {
            
            if(entity.isTreeEnabled()){
                parentId=entity.getTreeParentId();
                if(parentId==null||topId.equals(parentId)){
                    resultList.add(entity);
                }
            }
            
        }

        //获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setTreeChildren(getSubList(entity.getTreeId(),entityList));
        }
        return resultList;
    }

    private  static  <E extends TreeEntity<E>>  List<E> getSubList(Integer id, List<E> entityList) {
        List<E> childList=new ArrayList<>();
        Integer parentId;

        //子集的直接子对象
        for (E entity : entityList) {
            parentId=entity.getTreeParentId();
            if(id.equals(parentId)){
                childList.add(entity);
            }
        }

        //子集的间接子对象
        for (E entity : childList) {
            entity.setTreeChildren(getSubList(entity.getTreeId(), entityList));
        }

        //递归退出条件
        if(childList.size()==0){
            return null;
        }

        return childList;
    }
}
package com.xj.groupbuy.service;

import com.xj.groupbuy.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-09
 */
public interface ICategoryService extends IService<Category> {

    List<Category> getCategoryTree();
}

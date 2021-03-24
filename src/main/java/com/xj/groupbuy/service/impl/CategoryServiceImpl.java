package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.common.util.TreeUtil;
import com.xj.groupbuy.entity.Category;
import com.xj.groupbuy.mapper.CategoryMapper;
import com.xj.groupbuy.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-09
 */
@Service
@Primary
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Override
    public List<Category> getCategoryTree() {
        List<Category> list = this.list();

        return TreeUtil.getTreeList(0, list);

    }

}

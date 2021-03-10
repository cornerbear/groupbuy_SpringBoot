package com.xj.groupbuy.background.category.service.impl;

import com.xj.groupbuy.background.category.entity.Category;
import com.xj.groupbuy.background.category.mapper.CategoryMapper;
import com.xj.groupbuy.background.category.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        List<Category> res = new LinkedList<>();
        List<Category> sons = new LinkedList<>();
        List<Category> list = this.list();
        for (Category category : list) {
            if (category.getParentCatId().equals("0")) {
                res.add(category);
            } else {
                sons.add(category);
            }
        }
        for (Category parent : res) {
            recursionCategory(parent,sons);
        }
        return res;
    }

    public void recursionCategory(Category parent, List<Category> sons) {
        if(sons.size() == 0) return ;
        String catId = parent.getCatId();
        for (Category son : sons) {
            if (son.getParentCatId().equals(catId)) {
                if(parent.getChildren() == null) 
                    parent.setChildren(new LinkedList<>());
                parent.getChildren().add(son);
                sons.remove(son);
                recursionCategory(son,sons);
            }
        }
    }
}

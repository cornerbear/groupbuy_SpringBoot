package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.common.util.TreeUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Category;
import com.xj.groupbuy.mapper.CategoryMapper;
import com.xj.groupbuy.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public List<Category> getCategoryTree() {
        List<Category> list = this.list();

        return TreeUtil.getTreeList(0, list);

    }

    @Override
    public CommonVO getAllLevelByCatId(Integer id) {
        Map<String, Object> parentLevelId = categoryMapper.getParentLevelId(id);
        Map<String,Object> result = new HashMap<>();

        result.put("oneOptions",this.getAllOne());
        if(parentLevelId != null){
            result.put("twoOptions",this.getTwoByOne(Integer.parseInt(parentLevelId.get("one").toString())));
            result.put("threeOptions",this.getThreeByTwo(Integer.parseInt(parentLevelId.get("two").toString())));
            result.put("checkPath",parentLevelId);
        }

        return new CommonVO(true,result);
    }
    
    @Override
    public List<Map<String,Object>> getAllOne() {
        return categoryMapper.getAllOne();
    }

    @Override
    public List<Map<String, Object>> getTwoByOne(Integer one) {
        return categoryMapper.getTwoByOne(one);
    }

    @Override
    public List<Map<String, Object>> getThreeByTwo(Integer two) {
        return categoryMapper.getThreeByTwo(two);
    }

}

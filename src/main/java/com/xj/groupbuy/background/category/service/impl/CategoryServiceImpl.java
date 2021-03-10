package com.xj.groupbuy.background.category.service.impl;

import com.xj.groupbuy.background.category.entity.Category;
import com.xj.groupbuy.background.category.mapper.CategoryMapper;
import com.xj.groupbuy.background.category.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-09
 */
@Service
@Primary
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}

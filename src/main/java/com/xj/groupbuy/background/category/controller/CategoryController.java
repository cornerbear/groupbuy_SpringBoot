package com.xj.groupbuy.background.category.controller;


import com.xj.groupbuy.background.category.entity.Category;
import com.xj.groupbuy.background.category.service.ICategoryService;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.foreground.cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-09
 */
@RestController
@RequestMapping("admin")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;
    
    @RequestMapping("categoryAll")
    public List<Category> getCategoryAll(){
        return categoryService.list();
    }
    
    @PostMapping("category")
    public CommonVO addCategory(@RequestBody Category category){

        boolean save = categoryService.save(category);
        return new CommonVO(save,save?"保存成功":"保存失败");
    }

}

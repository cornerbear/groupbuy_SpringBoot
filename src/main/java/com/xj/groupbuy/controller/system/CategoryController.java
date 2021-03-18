package com.xj.groupbuy.controller.system;


import com.xj.groupbuy.entity.Category;
import com.xj.groupbuy.service.ICategoryService;
import com.xj.groupbuy.common.vo.CommonVO;
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
@CrossOrigin
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;
    
    @RequestMapping("categoryAll")
    public List<Category> getCategoryAll(){
        return categoryService.list();
    }
    
    @RequestMapping("categoryTree")
    public CommonVO getCategoryTree(){
        List<Category> list = categoryService.getCategoryTree();
        return new CommonVO(true,list);
    }
    
    @PostMapping("category")
    public CommonVO addCategory(@RequestBody Category category){

        boolean save = categoryService.save(category);
        return new CommonVO(save,save?"保存成功":"保存失败");
    }

}

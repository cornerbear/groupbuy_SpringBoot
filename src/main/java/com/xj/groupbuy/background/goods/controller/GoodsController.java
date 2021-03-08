package com.xj.groupbuy.background.goods.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.background.goods.entity.Goods;
import com.xj.groupbuy.background.goods.service.IGoodsService;
import com.xj.groupbuy.background.test.bean.TTest;
import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-08
 */
@RestController
@RequestMapping("/admin")
public class GoodsController {
    
    @Autowired
    private IGoodsService goodsService;

    @PostMapping("goods")
    public CommonVO saveGoods(@RequestBody Goods goods){

        goods.setGoodsStoreId("1");
        
        boolean save = goodsService.save(goods);
        String resMsg = save?"保存成功":"保存失败";
        return new CommonVO(save,resMsg);
    }
    
    @GetMapping("goods/all/{pageNo}/{pageSize}")
    public Page<Goods> getGoodsAll(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize){

        // 分页查询数据 第几页，每页几个
        Page<Goods> paramPage = new Page<>(pageNo,pageSize);
        return goodsService.page(paramPage, null);
    }
    
    
}

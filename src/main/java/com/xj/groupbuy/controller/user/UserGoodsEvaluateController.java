package com.xj.groupbuy.controller.user;


import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.GoodsEvaluate;
import com.xj.groupbuy.service.IGoodsEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-24
 */
@RestController
@RequestMapping("user/goods/evaluate")
public class UserGoodsEvaluateController {

    @Autowired
    private IGoodsEvaluateService goodsEvaluateService;
    
    @GetMapping("{goodsId}")
    public CommonVO getGoodsEvaluate(@PathVariable Integer goodsId){
        return goodsEvaluateService.getGoodsEvaluate(goodsId);
    }
    
    @PostMapping
    public CommonVO saveUserGoodsEvaluate(@RequestBody GoodsEvaluate goodsEvaluate){
        return goodsEvaluateService.saveUserGoodsEvaluate(goodsEvaluate);
    }
}

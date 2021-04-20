package com.xj.groupbuy.controller.store;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.util.DateUtil;
import com.xj.groupbuy.common.util.FileUtil;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.service.IGoodsService;
import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-08
 */
@RestController
@RequestMapping("store/goods")
public class GoodsController {
    
    @Autowired
    private IGoodsService goodsService;

    @PostMapping
    public CommonVO saveGoods(@RequestBody Goods goods){

        goods.setGoodsStoreId(UserUtil.getUserId());
        goods.setUpdateTime(DateUtil.getCurrentDate());
        
        boolean save = goodsService.save(goods);
        String resMsg = save?"保存成功":"保存失败";
        return new CommonVO(save,resMsg);
    }

    @DeleteMapping("{id}")
    public CommonVO deleteGoods(@PathVariable Integer id){

        return goodsService.deleteGoods(id);
    }
    @PostMapping("update")
    public CommonVO updateGoods(@RequestParam("files") MultipartFile[] multipartFiles,@RequestParam("goods") String goodsJson){
        Goods goods = JSONObject.parseObject(goodsJson, Goods.class);
        return goodsService.updateGoods(multipartFiles,goods);
    }
    @GetMapping("{id}")
    public CommonVO getGoods(@PathVariable Integer id){

        Goods goods = goodsService.getById(id);
        return new CommonVO(true,goods);
    }
    @GetMapping("all/{pageNo}/{pageSize}")
    public Page<Goods> getGoodsAll(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize){

        Page<Goods> page = new Page<>(pageNo,pageSize);
        return goodsService.page(page,new QueryWrapper<Goods>().eq("GOODS_STORE_ID",UserUtil.getUserId()));
    }
    
    
}

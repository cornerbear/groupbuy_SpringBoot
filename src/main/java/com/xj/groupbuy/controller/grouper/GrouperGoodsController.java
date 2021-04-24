package com.xj.groupbuy.controller.grouper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.CommunityGoods;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.service.ICommunityGoodsService;
import com.xj.groupbuy.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/20
 */
@RestController
@RequestMapping("grouper/goods")
public class GrouperGoodsController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ICommunityGoodsService communityGoodsService;

    @GetMapping("all/{pageNo}/{pageSize}/{select}")
    public Page<Goods> getCommunityGoods(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize, @PathVariable(value = "select") Boolean select){

        Page<Goods> page = new Page<>(pageNo,pageSize);
        return goodsService.getCommunityGoods(select,page);
    }
    @DeleteMapping("deleteCommunityGoods/{id}")
    public CommonVO deleteCommunityGoods(@PathVariable Integer id){
        boolean remove = communityGoodsService.remove(new QueryWrapper<CommunityGoods>().eq("goods_id", id).eq("community_id", UserUtil.getCurrentUser().getCommunityId()));
        return new CommonVO(remove,remove?"删除成功":"删除失败");
    }
    @PostMapping("addCommunityGoods/{id}")
    public CommonVO addCommunityGoods(@PathVariable Integer id){
        int count = communityGoodsService.count(new QueryWrapper<CommunityGoods>().eq("goods_id", id).eq("community_id", UserUtil.getCurrentUser().getCommunityId()));
        if(count == 0){

            boolean save = communityGoodsService.save(new CommunityGoods(id, UserUtil.getCurrentUser().getCommunityId()));
            return new CommonVO(save,save?"添加成功":"添加失败");
        } else {
            return new CommonVO(false,"社区已存在该商品");
        }
    }
}

package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xj.groupbuy.common.util.DateUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.GoodsEvaluate;
import com.xj.groupbuy.mapper.GoodsEvaluateMapper;
import com.xj.groupbuy.service.IGoodsEvaluateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-24
 */
@Service
@Primary
public class GoodsEvaluateServiceImpl extends ServiceImpl<GoodsEvaluateMapper, GoodsEvaluate> implements IGoodsEvaluateService {

    @Autowired
    private GoodsEvaluateMapper goodsEvaluateMapper;
    
    @Override
    public CommonVO saveUserGoodsEvaluate(GoodsEvaluate goodsEvaluate) {
        
        goodsEvaluate.setCreateTime(DateUtil.getCurrentDate());
        goodsEvaluate.setUpdateTime(DateUtil.getCurrentDate());

        int insert = goodsEvaluateMapper.insert(goodsEvaluate);

        return new CommonVO(insert==1,insert==1?"评价成功":"评价失败");
    }

    @Override
    public CommonVO getGoodsEvaluate(Integer goodsId) {
        List<?> goodsEvaluates = goodsEvaluateMapper.getGoodsEvaluate(goodsId);
        return new CommonVO(true,goodsEvaluates);
    }
}

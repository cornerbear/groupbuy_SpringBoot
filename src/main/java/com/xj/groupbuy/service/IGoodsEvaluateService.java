package com.xj.groupbuy.service;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.GoodsEvaluate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-24
 */
public interface IGoodsEvaluateService extends IService<GoodsEvaluate> {

    CommonVO saveUserGoodsEvaluate(GoodsEvaluate goodsEvaluate);

    CommonVO getGoodsEvaluate(Integer goodsId);
}

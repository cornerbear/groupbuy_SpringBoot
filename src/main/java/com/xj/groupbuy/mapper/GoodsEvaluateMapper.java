package com.xj.groupbuy.mapper;

import com.xj.groupbuy.entity.GoodsEvaluate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-24
 */
@Mapper
public interface GoodsEvaluateMapper extends BaseMapper<GoodsEvaluate> {

    List<?> getGoodsEvaluate(Integer goodsId);
}

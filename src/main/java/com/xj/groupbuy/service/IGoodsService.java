package com.xj.groupbuy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-08
 */
public interface IGoodsService extends IService<Goods> {

    CommonVO updateGoods(MultipartFile[] multipartFiles, Goods goods);

    CommonVO deleteGoods(Integer id);

    Page<Goods> getCommunityGoods(Boolean select, Page<Goods> page);

    Page<Goods> getUserCommunityGoods(Page<Goods> page);

}

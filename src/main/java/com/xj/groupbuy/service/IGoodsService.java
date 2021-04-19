package com.xj.groupbuy.service;

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
}

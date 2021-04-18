package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.common.properties.FileProperties;
import com.xj.groupbuy.common.util.FileUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.mapper.GoodsMapper;
import com.xj.groupbuy.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-08
 */
@Service
@Primary
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private FileProperties fileProperties;
    @Override
    public CommonVO updateGoods(MultipartFile[] multipartFiles, Goods goods) {

        for (MultipartFile multipartFile : multipartFiles) {
            FileUtil.upload(multipartFile,fileProperties.getPath(),multipartFile.getOriginalFilename());
        }
        System.out.println(goods);
        return null;
    }
}

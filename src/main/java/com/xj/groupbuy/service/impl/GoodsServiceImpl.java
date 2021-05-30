package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.properties.FileProperties;
import com.xj.groupbuy.common.util.DateUtil;
import com.xj.groupbuy.common.util.FileUtil;
import com.xj.groupbuy.common.util.NullUtils;
import com.xj.groupbuy.common.util.UserUtil;
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
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public CommonVO updateGoods(MultipartFile[] multipartFiles, Goods goods) {

        if(multipartFiles != null){

            String uploadPath = null;
            for (MultipartFile multipartFile : multipartFiles) {
                uploadPath = FileUtil.upload(multipartFile, fileProperties.getPath(), multipartFile.getOriginalFilename());
            }
            if(NullUtils.isNotEmpty(goods.getGoodsImg())){
                // 删除旧照片
                FileUtil.delete(goods.getGoodsImg());
            }
            goods.setGoodsImg(uploadPath);
        }
        goods.setUpdateTime(DateUtil.getCurrentDate());
        int i = goodsMapper.updateById(goods);
        return new CommonVO(i==1,i==1?"修改成功":"修改失败");
    }

    @Override
    public CommonVO deleteGoods(Integer id) {
        Goods goods = goodsMapper.selectById(id);
        String imgPath = goods.getGoodsImg();
        int i = goodsMapper.deleteById(id);
        if(i == 1){
            FileUtil.delete(imgPath);
            return new CommonVO(true,"删除成功");
        } else {
            return new CommonVO(false,"删除失败");
        }
    }

    @Override
    public Page<Goods> getCommunityGoods(Boolean select, Page<Goods> page) {
        
        if(select){
            Integer communityId = UserUtil.getCurrentUser().getCommunityId();
            return goodsMapper.getCommunityGoods(communityId,page);
        } else {
            return goodsMapper.getStoreGoods(page);
        }
    }

    @Override
    public Page<Goods> getUserCommunityGoods(Page<Goods> page) {
        Integer communityId = UserUtil.getCurrentUser().getCommunityId();
        return goodsMapper.getCommunityGoods(communityId,page);
    }

}

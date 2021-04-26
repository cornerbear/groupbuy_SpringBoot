package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.common.util.DateUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.mapper.GoodsMapper;
import com.xj.groupbuy.service.IDataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/26
 */
@Service
@Primary
public class DataAnalysisServiceImpl implements IDataAnalysisService {
    
    @Autowired
    private GoodsMapper goodsMapper;
    
    @Override
    public CommonVO getStoreDayAnalysis(String storeId) {

        String date = DateUtil.format(new Date(), "yyyy-MM-dd");
        List<Map<String,Object>> data = goodsMapper.getStoreDayData(storeId,date);
        List<Object> goodsNames  = new ArrayList<>();
        List<Object> goodsSales  = new ArrayList<>();
        List<Object> goodsMoneys  = new ArrayList<>(); 
        for (Map<String, Object> map : data) {
            goodsNames.add(map.get("goodsName"));
            goodsSales.add(map.get("goodsSale"));
            goodsMoneys.add(map.get("goodsMoney"));
        }
        Map<String,Object> result = new HashMap<>();
        result.put("goodsNames",goodsNames);
        result.put("goodsSales",goodsSales);
        result.put("goodsMoneys",goodsMoneys);
        return new CommonVO(true,result);
    }

    @Override
    public CommonVO getStoreMonthAnalysis(String storeId) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(m);
        
        List<Map<String,Object>> data = goodsMapper.getStoreMonthAnalysis(storeId,date);
        List<Object> goodsNames  = new ArrayList<>();
        List<Object> goodsSales  = new ArrayList<>();
        List<Object> goodsMoneys  = new ArrayList<>();
        for (Map<String, Object> map : data) {
            goodsNames.add(map.get("goodsName"));
            goodsSales.add(map.get("goodsSale"));
            goodsMoneys.add(map.get("goodsMoney"));
        }
        Map<String,Object> result = new HashMap<>();
        result.put("goodsNames",goodsNames);
        result.put("goodsSales",goodsSales);
        result.put("goodsMoneys",goodsMoneys);
        return new CommonVO(true,result);
    }
}

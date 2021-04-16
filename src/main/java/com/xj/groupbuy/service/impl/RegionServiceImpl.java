package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.mapper.RegionMapper;
import com.xj.groupbuy.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/16
 */
@Service
@Primary
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private RegionMapper regionMapper;
    
    @Override
    public List<Map<String,Object>> getAllProvinces() {
        return regionMapper.getAllProvinces();
    }

    @Override
    public List<Map<String, Object>> getCitysByProvinceCode(String provinceCode) {
        return regionMapper.getCitysByProvinceCode(provinceCode);
    }

    @Override
    public List<Map<String, Object>> getAreasByCityCode(String cityCode) {
        return regionMapper.getAreasByCityCode(cityCode);
    }

    @Override
    public List<Map<String, Object>> getStreetsByAreaCode(String areaCode) {
        return regionMapper.getStreetsByAreaCode(areaCode);
    }
}

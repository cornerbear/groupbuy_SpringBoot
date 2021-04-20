package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Menu;
import com.xj.groupbuy.mapper.CommunityMapper;
import com.xj.groupbuy.mapper.RegionMapper;
import com.xj.groupbuy.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    @Autowired
    private CommunityMapper communityMapper;
    
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

    @Override
    public CommonVO getAllLevelByCommunityId(Integer id) {
        Map<String, Object> parentLevelId = communityMapper.getParentLevelId(id);
        Map<String,Object> result = new HashMap<>();
        
        result.put("provinceOptions",this.getAllProvinces());
        if(parentLevelId != null){
            result.put("cityOptions",this.getCitysByProvinceCode(parentLevelId.get("provinceCode").toString()));
            result.put("areaOptions",this.getAreasByCityCode(parentLevelId.get("cityCode").toString()));
            result.put("streetOptions",this.getStreetsByAreaCode(parentLevelId.get("areaCode").toString()));
            result.put("checkPath",parentLevelId);
        }
        
        return new CommonVO(true,result);
    }

    @Override
    public List<Map<String, Object>> getCommunityByStreetCode(String streetCode) {
        return regionMapper.getCommunityByStreetCode(streetCode);
    }
}

package com.xj.groupbuy.controller.system;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/16
 */
@RestController
@RequestMapping("system/region")
public class RegionController {
    
    @Autowired
    private IRegionService regionService;

    @GetMapping("allLevel/{id}")
    public CommonVO getAllLevelByCommunityId(@PathVariable Integer id){
        return regionService.getAllLevelByCommunityId(id);
    }
    
    @GetMapping("province")
    public CommonVO getAllProvinces(){
        List<Map<String, Object>> result = regionService.getAllProvinces();
        return new CommonVO(true,result);
    }

    @GetMapping("city/{provinceCode}")
    public CommonVO getCitysByProvinceCode(@PathVariable String provinceCode){
        List<Map<String, Object>> result = regionService.getCitysByProvinceCode(provinceCode);
        return new CommonVO(true,result);
    }
    @GetMapping("area/{cityCode}")
    public CommonVO getAreasByCityCode(@PathVariable String cityCode){
        List<Map<String, Object>> result = regionService.getAreasByCityCode(cityCode);
        return new CommonVO(true,result);
    }
    @GetMapping("street/{areaCode}")
    public CommonVO getStreetsByAreaCode(@PathVariable String areaCode){
        List<Map<String, Object>> result = regionService.getStreetsByAreaCode(areaCode);
        return new CommonVO(true,result);
    }
}

package com.xj.groupbuy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.entity.Community;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-15
 */
@Mapper
public interface CommunityMapper extends BaseMapper<Community> {

    IPage<?> getTable(String provinceCode, String cityCode, String areaCode, String streetCode, Page<Map<String, Object>> page);

    Map<String, Object> getParentLevelId(Integer id);
    
    Map<String, String> getParentLevelName(Integer id);
}

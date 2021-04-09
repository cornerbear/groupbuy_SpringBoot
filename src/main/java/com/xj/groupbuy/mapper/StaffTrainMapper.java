package com.xj.groupbuy.mapper;

import com.xj.groupbuy.entity.StaffTrain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-07
 */
@Mapper
public interface StaffTrainMapper extends BaseMapper<StaffTrain> {

    List<StaffTrain> getStaffTrainDetail(Integer trainId);
}

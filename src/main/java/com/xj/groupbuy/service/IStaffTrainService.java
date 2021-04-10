package com.xj.groupbuy.service;

import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.StaffTrain;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-07
 */
public interface IStaffTrainService extends IService<StaffTrain> {

    CommonVO getStaffTrainDetail(Integer trainId);

    CommonVO saveTrainAndUpload(MultipartFile[] multipartFiles, String trainName, String trainContent);

    CommonVO deleteStaffTrain(Integer trainId);
}

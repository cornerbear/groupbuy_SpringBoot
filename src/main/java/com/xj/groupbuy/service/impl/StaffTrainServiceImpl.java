package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.StaffTrain;
import com.xj.groupbuy.entity.TrainFile;
import com.xj.groupbuy.mapper.StaffTrainMapper;
import com.xj.groupbuy.mapper.TrainFileMapper;
import com.xj.groupbuy.service.IStaffTrainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-07
 */
@Service
@Primary
public class StaffTrainServiceImpl extends ServiceImpl<StaffTrainMapper, StaffTrain> implements IStaffTrainService {

    @Autowired
    private StaffTrainMapper staffTrainMapper;
    @Autowired
    private TrainFileMapper trainFileMapper;
    
    @Override
    public CommonVO saveStaffTrain(String uploadFileName,String trainName, String trainContent) {
        if(uploadFileName == null){
            return new CommonVO(false,"文件上传失败");
        } else {
            StaffTrain staffTrain = new StaffTrain(trainName,trainContent, UserUtil.getCurrentUser().getUserId());
            int insert = staffTrainMapper.insert(staffTrain);
            TrainFile trainFile = new TrainFile(staffTrain.getId(),uploadFileName);
            trainFileMapper.insert(trainFile);
            return new CommonVO(insert==1,insert==1?"保存成功":"保存失败");
        }
    }
}

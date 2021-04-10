package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.common.properties.FileProperties;
import com.xj.groupbuy.common.util.FileUtil;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
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
    @Autowired
    private FileProperties fileProperties;

    @Override
    public CommonVO getStaffTrainDetail(Integer trainId) {

        List<StaffTrain> staffTrainsWithFiles = staffTrainMapper.getStaffTrainDetail(trainId);

        // 处理一下path
        for (StaffTrain staffTrainsWithFile : staffTrainsWithFiles) {
            for (TrainFile file : staffTrainsWithFile.getFiles()) {
                String filePath = file.getFilePath();
                int i = filePath.lastIndexOf(".");
                int j = filePath.lastIndexOf("/");
                filePath = filePath.substring(j + 1, i - 11) + filePath.substring(i);
                file.setFilePath(filePath);
            }
        }

        return new CommonVO(true, staffTrainsWithFiles.get(0));
    }

    @Override
    public CommonVO saveTrainAndUpload(MultipartFile[] multipartFiles, String trainName, String trainContent) {

        StaffTrain staffTrain = new StaffTrain(trainName, trainContent, UserUtil.getCurrentUser().getUserId());
        staffTrainMapper.insert(staffTrain);
        
        Integer trainId = staffTrain.getId();

        for (MultipartFile multipartFile : multipartFiles) {
            String uploadFilePath = FileUtil.upload(multipartFile, fileProperties.getPath(), multipartFile.getOriginalFilename());
            trainFileMapper.insert(new TrainFile(trainId,uploadFilePath));
        }

        return new CommonVO(true,"发布成功");
    }

    @Override
    public CommonVO deleteStaffTrain(Integer trainId) {

        List<StaffTrain> staffTrainsWithFiles = staffTrainMapper.getStaffTrainDetail(trainId);
        StaffTrain staffTrain = staffTrainsWithFiles.get(0);
        for (TrainFile file : staffTrain.getFiles()) {
            String filePath = file.getFilePath();
            // 删除培训文件数据库
            trainFileMapper.deleteById(file.getId());
            
            // 删除计算机文件
            FileUtil.delete(filePath);
        }
        
        // 删除培训
        staffTrainMapper.deleteById(trainId);
        return new CommonVO(true,"删除成功");
    }
}

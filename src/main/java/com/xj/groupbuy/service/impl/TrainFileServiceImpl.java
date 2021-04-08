package com.xj.groupbuy.service.impl;

import com.xj.groupbuy.entity.TrainFile;
import com.xj.groupbuy.mapper.TrainFileMapper;
import com.xj.groupbuy.service.ITrainFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TrainFileServiceImpl extends ServiceImpl<TrainFileMapper, TrainFile> implements ITrainFileService {

}

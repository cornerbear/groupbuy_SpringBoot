package com.xj.groupbuy.controller.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.properties.FileProperties;
import com.xj.groupbuy.common.util.FileUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.StaffTrain;
import com.xj.groupbuy.service.IStaffTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-07
 */
@RestController
@RequestMapping("manager/staffTrain")
public class StaffTrainController {
    
    @Autowired
    private FileProperties fileProperties;
    @Autowired
    private IStaffTrainService staffTrainService;

    @RequestMapping("upload")
    public CommonVO fileUpload(@RequestParam("file") MultipartFile multipartFile, String trainName, String trainContent){

        if(multipartFile==null)
            return new CommonVO(false,"文件上传失败");
        String uploadFileName = FileUtil.upload(multipartFile, fileProperties.getPath(), multipartFile.getOriginalFilename());

        return staffTrainService.saveStaffTrain(uploadFileName,trainName,trainContent);

//        return new CommonVO(upload,upload?"上传成功":"上传失败");
    }
    
    @GetMapping("all/{pageNo}/{pageSize}")
    public Page<?> getAllStaffTrain(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize){
        Page<StaffTrain> paramPage = new Page<>(pageNo,pageSize);
        return staffTrainService.page(paramPage, null);
    }
}

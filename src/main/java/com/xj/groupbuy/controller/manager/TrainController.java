package com.xj.groupbuy.controller.manager;

<<<<<<< HEAD:src/main/java/com/xj/groupbuy/controller/manager/TrainController.java
import com.xj.groupbuy.common.properties.FileProperties;
import com.xj.groupbuy.common.util.FileUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
=======

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.properties.FileProperties;
import com.xj.groupbuy.common.util.FileUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Goods;
import com.xj.groupbuy.entity.StaffTrain;
import com.xj.groupbuy.service.IStaffTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

>>>>>>> parent of 8d96200 (Revert "0.3.2 ？？？"):src/main/java/com/xj/groupbuy/controller/manager/StaffTrainController.java
import org.springframework.web.multipart.MultipartFile;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/7
 */
@RestController
@RequestMapping("manager/train")
public class TrainController {

    @Autowired
    private FileProperties fileProperties;

    @RequestMapping("upload")
    public CommonVO fileUpload(@RequestParam("file") MultipartFile multipartFile){

        if(multipartFile==null)
            return new CommonVO(false,"文件上传失败");
        boolean upload = FileUtil.upload(multipartFile, fileProperties.getPath(), multipartFile.getOriginalFilename());
        
        return new CommonVO(upload,upload?"上传成功":"上传失败");
    }
    
    @GetMapping("all/{pageNo}/{pageSize}")
    public Page<?> getAllStaffTrain(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize){
        Page<StaffTrain> paramPage = new Page<>(pageNo,pageSize);
        return staffTrainService.page(paramPage, null);
    }
}

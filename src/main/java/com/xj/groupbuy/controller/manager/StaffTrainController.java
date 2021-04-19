package com.xj.groupbuy.controller.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.properties.FileProperties;
import com.xj.groupbuy.common.util.FileUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.StaffTrain;
import com.xj.groupbuy.entity.TrainFile;
import com.xj.groupbuy.service.IStaffTrainService;
import com.xj.groupbuy.service.ITrainFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Properties;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-07
 */
@Controller
@RequestMapping("manager/staffTrain")
public class StaffTrainController {

    @Autowired
    private IStaffTrainService staffTrainService;
    @Autowired
    private ITrainFileService trainFileService;
    @Autowired
    private FileProperties fileProperties;

    @RequestMapping("upload")
    @ResponseBody
    public CommonVO fileUpload(@RequestParam("files") MultipartFile[] multipartFiles, String trainName, String trainContent) {

        return staffTrainService.saveTrainAndUpload(multipartFiles,trainName,trainContent);
        
    }

    @RequestMapping("download")
    public ResponseEntity<Object> fileDownload(@RequestParam("trainFileId") Integer trainFileId, HttpServletResponse response) {

        TrainFile trainFile = trainFileService.getById(trainFileId);
        if (trainFile == null) {
            return null;
        }
        File file = new File(trainFile.getFilePath());
        // 文件下载
        if (file.isFile()) {
            return FileUtil.download(fileProperties.getPath(),trainFile.getFilePath());
        }

        // 文件夹压缩成zip下载
//        if (file.isDirectory()) {
//            String parent = file.getParent();
//            // 创建临时存放文件夹
//            File temDir = new File(parent + "/" + taskId);
//            if (!temDir.exists()) {
//                temDir.mkdirs();
//            }
//            // 将需要下载的文件夹和内容拷贝到临时文件夹中
//            try {
//                Download.copyDir(dataInfo.getResponseUrl(), parent + "/" + taskId);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            // 设置头部格式
//            response.setContentType("application/zip");
//            response.setHeader("Content-Disposition", "attachment; filename="+taskId+".zip");
//            // 调用工具类，下载zip压缩包
//            try {
//                Download.toZip(temDir.getPath(), response.getOutputStream(), true);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            // 删除临时文件夹和内容
//            Download.delAllFile(new File(parent + "/" + taskId));
//        }
        return null;
    }

    @GetMapping("all/{pageNo}/{pageSize}")
    @ResponseBody
    public Page<?> getAllStaffTrain(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize) {
        Page<StaffTrain> paramPage = new Page<>(pageNo, pageSize);
        return staffTrainService.page(paramPage, null);
    }

    @GetMapping("{trainId}")
    @ResponseBody
    public CommonVO getStaffTrainDetail(@PathVariable Integer trainId) {

        return staffTrainService.getStaffTrainDetail(trainId);
    }

    @DeleteMapping("{trainId}")
    @ResponseBody
    public CommonVO deleteStaffTrain(@PathVariable Integer trainId) {

        return staffTrainService.deleteStaffTrain(trainId);
    }
}

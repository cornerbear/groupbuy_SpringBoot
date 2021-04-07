package com.xj.groupbuy.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/7
 */
public class FileUtil {
    
    /**
     * @param file     文件
     * @param path     文件存放路径
     * @param fileName 源文件名
     * @return
     */
    public static String upload(MultipartFile file, String path, String fileName) {
        
        // 生成新的文件名
        String realPath = path + "/" + FileNameUtil.getFileName(fileName);

        //使用原文件名
//        String realPath = path + "/" + fileName;

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return realPath;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

}
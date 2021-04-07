package com.xj.groupbuy.common.util;

import java.util.UUID;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/7
 */
public class FileNameUtil {
    
    /**
     * 获取文件后缀
     * @param fileName 文件名
     * @return 
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
    public static String getPrefix(String fileName){
        return fileName.substring(0,fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
        String prefix = FileNameUtil.getPrefix(fileOriginName);
        String uuid = "_" + UUID.randomUUID().toString().substring(26);
        String suffix = FileNameUtil.getSuffix(fileOriginName);
        return  prefix + uuid + suffix;
    }
}

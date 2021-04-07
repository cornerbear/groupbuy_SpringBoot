package com.xj.groupbuy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("train_file")
public class TrainFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    
    @TableField("TRAIN_ID")
    private Integer trainId;

    @TableField("FILE_PATH")
    private String filePath;


    public TrainFile(Integer trainId, String uploadFileName) {
        this.trainId = trainId;
        this.filePath = uploadFileName;
    }
}

package com.xj.groupbuy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xj.groupbuy.common.util.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("staff_train")
public class StaffTrain implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    
    @TableField("TRAIN_NAME")
    private String trainName;

    @TableField("TRAIN_CONTENT")
    private String trainContent;
    
    @TableField("CREATE_TIME")
    private String createTime;
    
    @TableField("CREATE_USER_ID")
    private String createUserId;
    
    @TableField(exist = false)
    private List<TrainFile> files;


    public StaffTrain(String trainName, String trainContent, String userId) {
        this.trainName = trainName;
        this.trainContent = trainContent;
        this.createUserId = userId;
        this.createTime = DateUtil.getCurrentDate();
    }
}

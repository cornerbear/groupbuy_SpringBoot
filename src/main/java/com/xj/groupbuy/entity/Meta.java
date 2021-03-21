package com.xj.groupbuy.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/20
 */

@Data
public class Meta implements Serializable {
    
    private static final long serialVersionUID = 4481526064896844012L;
    
    private Boolean keepAlive;

    private Boolean requireAuth;
}

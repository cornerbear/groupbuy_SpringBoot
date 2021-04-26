package com.xj.groupbuy.service;

import com.xj.groupbuy.common.vo.CommonVO;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/26
 */
public interface IDataAnalysisService {
    CommonVO getStoreDayAnalysis(String storeId);

    CommonVO getStoreMonthAnalysis(String storeId);
}

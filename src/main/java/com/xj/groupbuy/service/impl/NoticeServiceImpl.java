package com.xj.groupbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.util.DateUtil;
import com.xj.groupbuy.common.util.UserUtil;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Notice;
import com.xj.groupbuy.mapper.NoticeMapper;
import com.xj.groupbuy.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-25
 */
@Service
@Primary
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    
    @Override
    public CommonVO addNotice(Notice notice) {
        notice.setActionUserId(UserUtil.getUserId());
        notice.setCreateTime(DateUtil.getCurrentDate());

        int insert = noticeMapper.insert(notice);
        return new CommonVO(insert==1,insert==1?"添加成功":"添加失败");
    }

    @Override
    public IPage<?> getNotices(Integer mode, Integer pageNo, Integer pageSize) {
        Page<Notice> page = new Page<>(pageNo, pageSize);
        if(mode == null || mode == 0){
            return noticeMapper.selectPage(page,null);
        } else {
            return noticeMapper.selectPage(page,new QueryWrapper<Notice>().eq("mode",mode));
        }
    }
}

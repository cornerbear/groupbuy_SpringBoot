package com.xj.groupbuy.controller.manager;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Notice;
import com.xj.groupbuy.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-25
 */
@RestController
@RequestMapping("manager/notice")
public class ManagerNoticeController {
    
    @Autowired
    private INoticeService noticeService;
    
    @PostMapping
    public CommonVO addNotice(@RequestBody Notice notice){
        return noticeService.addNotice(notice);
    }
    @DeleteMapping("{id}")
    public CommonVO deleteNotice(@PathVariable Integer id){
        boolean b = noticeService.removeById(id);
        return new CommonVO(b,b?"删除成功":"删除失败");
    }

    @GetMapping("all/{pageNo}/{pageSize}")
    public IPage<?> getNotices(Integer mode,@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize) {
        return noticeService.getNotices(mode,pageNo,pageSize);
    }
}

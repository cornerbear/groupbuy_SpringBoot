package com.xj.groupbuy.controller.manager;


import com.xj.groupbuy.common.vo.CommonVO;
import com.xj.groupbuy.entity.Community;
import com.xj.groupbuy.service.ICommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-04-15
 */
@RestController
@RequestMapping("manager/community")
public class CommunityController {
    
    @Autowired
    private ICommunityService communityService;
    
    @PostMapping
    public CommonVO addCommunity(@RequestBody Community community){
        boolean save = communityService.save(community);
        return new CommonVO(save,save?"保存成功":"保存失败");
    }

    @PutMapping
    public CommonVO updateCommunity(@RequestBody Community community){
        boolean update = communityService.updateById(community);
        return new CommonVO(update,update?"修改成功":"修改失败");
    }

    @DeleteMapping("{id}")
    public CommonVO deleteCommunity(@PathVariable Integer id){
        boolean remove = communityService.removeById(id);
        return new CommonVO(remove,remove?"删除成功":"删除失败");
    }
    
    @GetMapping("{id}")
    public CommonVO getCommunity(@PathVariable Integer id){
        Community byId = communityService.getById(id);
        return new CommonVO(true,byId);
    }

    @GetMapping("table")
    public CommonVO table(String level,String parentCode, Integer pageNo, Integer pageSize){
        return new CommonVO(true,communityService.table(level,parentCode,pageNo,pageSize));
    }
}

package com.shf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.shf.entity.*;
import com.shf.result.Result;
import com.shf.service.*;
import com.shf.vo.HouseQueryVo;
import com.shf.vo.HouseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/house")
@RestController
public class HouseController {
    @Reference
    HouseService houseService;
    @Reference
    CommunityService communityService;
    @Reference
    DictService dictService;
    @Reference
    HouseImageService   houseImageService;
    @Reference
    HouseBrokerService houseBrokerService;
    @Reference
    UserFollowService userFollowService;
    //主要的工作就是实现分页查询,分页查询的分两步,第一步前端的数据的导入,第二步后端的控制器.返回的.
   @RequestMapping("/list/{pageNum}/{pageSize}")
    public PageInfo<HouseVo> goPage(@PathVariable("pageNum")Integer pageNum, @PathVariable("pageSize")Integer pageSize,
                                  @RequestBody HouseQueryVo houseQueryVo){
             PageInfo<HouseVo> pageInfo= houseService.findpageList(pageNum,pageSize,houseQueryVo);

             return pageInfo;
   }

   @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id")Long id, HttpSession session){
       //要把数据给传过去,需要的数据有挺多的
//       获取房源信息
       House house = houseService.getById(id);
//       获取经纪人信息
       Community community = communityService.getById(house.getCommunityId());
       //获取图片信息
       List<HouseImage> houseImage1List = houseImageService.findList(id, 1);
       //获取房主的信息
       List<HouseBroker> houseBrokerList = houseBrokerService.findListByHouseId(id);
       Map map = new HashMap();
       map.put("house",house);
       map.put("community ",community);
       map.put("houseImage1List",houseImage1List);
       map.put("houseBrokerList",houseBrokerList);
       //为了动态的显示是否已经关注,我们需要做一个判断,首先先判断得到的useinfo对象是否为空
       UserInfo use =(UserInfo) session.getAttribute("user");
       Boolean isFollow =false;
       if(null!=use){
           //调用userFollow方法查询是否有对象
           UserFollow byId = userFollowService.getById(id);
           if(null!=byId){
               isFollow=true;
           }


       }
       map.put("isFollow",isFollow);
       return Result.ok(map);


   }
}

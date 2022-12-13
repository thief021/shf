package com.shf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.shf.entity.UserInfo;
import com.shf.result.Result;
import com.shf.service.UserFollowService;
import com.shf.vo.UserFollowVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userFollow")
public class UserFollowController {
    @Reference
    UserFollowService userFollowService;
    @RequestMapping("/auth/follow/{id}")
    public Result auth_follow(@PathVariable("id")Long id, HttpSession session, HttpServletRequest request){
//        我们需要做什么?第一步,首先检查他是否已登录
        //得到useifo对象,前面已经做了判断,这里已经是登录好的界面了.
        UserInfo use =(UserInfo) request.getSession().getAttribute("user");
        userFollowService.follow(use.getId(),id);
        return Result.ok();

    }
    @RequestMapping("/auth/list/{pageNum}/{pageSize}")
    public Result auth_list(@PathVariable("pageNum")Integer pagenum ,@PathVariable("pageSize")Integer pagesize, HttpSession session){

        //返回关注列表,首先判断是否登录,然后返回分页查询
        UserInfo userInfo =(UserInfo) session.getAttribute("user");
       PageInfo<UserFollowVo> userFollowVoPageInfo = userFollowService.findPageList(pagenum,pagesize,userInfo.getId());
        return Result.ok(userFollowVoPageInfo);

    }
    //退出关注
    @RequestMapping("/auth/cancelFollow/{id}")
    public Result auth_cancel(@PathVariable("id")Long id){
        //主要的就是做逻辑删除
        userFollowService.delete(id);
        return Result.ok();
    }
}

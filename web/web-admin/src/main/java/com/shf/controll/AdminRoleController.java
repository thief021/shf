package com.shf.controll;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shf.service.AdminRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminRoleController  {
    @Reference
    AdminRoleService adminRoleService;
    //返回到分配用户角色的页面
    @RequestMapping("/assignShow/{amdinId}")
    public String goassignShowPage(@PathVariable("amdinId")Long adminId, ModelMap map){
//        返回到用户管理的页面
//        因为需要上传ID到请求域中
        map.addAttribute("adminId",adminId);
        Map<String,Object> list =adminRoleService.findpageAdminRola(adminId);
        map.addAllAttributes(list);
        return "admin/assignShow";
    }
    //返回到保存用户角色的页面
    @RequestMapping("/assignRole")
    public String assignRole(Long adminId, Long[] roleIds){
        //一个adminid一个数据roleids
        adminRoleService.findAssignRole(adminId,roleIds);
        return "common/successPage";
    }
}

package com.shf.controll;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shf.entity.Admin;
import com.shf.entity.HouseBroker;
import com.shf.service.AdminService;
import com.shf.service.HouseBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RequestMapping("/houseBroker")
@Controller
public class houseBrokerController {
    @Reference
    AdminService adminService;
    @Reference
    HouseBrokerService houseBrokerService;
    @RequestMapping("/create")
    public String craete(@RequestParam("houseId") Long houseId , Map map){
//        把请求参数传入到url上
        map.put("houseId",houseId);
//        查询所有的admin
        List<Admin> adminList = adminService.findAll();
//        传到请求域中
        map.put("adminList",adminList);
        return "houseBroker/create";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id")Long id,Map map){
//        根据ID查询到对象然后传入到请求域中
        HouseBroker houseBroker = houseBrokerService.getById(id);
        map.put("houseBroker",houseBroker);
        List<Admin> adminList = adminService.findAll();
//        传到请求域中
        map.put("adminList",adminList);
        return "houseBroker/edit";


    }
    @RequestMapping("/update")
    public String update(HouseBroker houseBroker){
        //目的讲查询到的
        Admin byId = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerName(byId.getName());
        houseBroker.setBrokerHeadUrl(byId.getHeadUrl());
        houseBrokerService.update(houseBroker);
        return "common/successPage";
    }
    @RequestMapping("/save")
    public String save(HouseBroker houseBroker){
        Admin byId = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerName(byId.getName());
        houseBroker.setBrokerHeadUrl(byId.getHeadUrl());
        houseBrokerService.insert(houseBroker);
        return "common/successPage";
    }
    @RequestMapping("/delete/{houseId}/{brokerId}")
    public  String delete(@PathVariable("houseId")Long houseId,@PathVariable("brokerId")Long id){
        houseBrokerService.delete(id);
        return "redirect:/house"+houseId;
    }
}

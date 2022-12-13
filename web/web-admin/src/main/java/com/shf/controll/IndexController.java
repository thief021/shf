package com.shf.controll;



import com.alibaba.dubbo.config.annotation.Reference;
import com.shf.entity.Admin;
import com.shf.entity.Permission;
import com.shf.service.AdminService;
import com.shf.service.DictService;
import com.shf.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
@SuppressWarnings({"unchecked", "rawtypes"})
public class IndexController {
    @Reference
    AdminService adminService;
    @Reference
    PermissionService permissionService;

    private final static String PAGE_INDEX = "frame/index";
    private final static String PAGE_MAIN = "frame/main";

    /**
     * 框架首页
     *
     * @return
     */
    @RequestMapping("/test")
    public String index(Map map) {
//        Long id =1L;
//        Admin admin = adminService.getById(id);
//        从security中得到一个用户的验证对象authentication对象

//        从authentication对象中得到一个user对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

//        根据用户对象得到名字然后改机adminservice得到admin对象
        Admin admin = adminService.getAdminByName(user.getUsername());
        List<Permission> permissionList= permissionService.findpermissionByAdminId(admin.getId());
         map.put("admin",admin);
         map.put("permissionList",permissionList);
        return PAGE_INDEX;
    }

    /**
     * 框架主页
     *
     * @return
     */
    @GetMapping("/main")
    public String main() {

        return PAGE_MAIN;
    }
    //去登录页面
    @RequestMapping("/login")
    public String login(){
        return "frame/login";
    }
}
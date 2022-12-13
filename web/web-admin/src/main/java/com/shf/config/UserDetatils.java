package com.shf.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shf.entity.Admin;
import com.shf.service.AdminService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//@Component
//实现这个usedetailsService类可以security的用户和密码的验证
public class UserDetatils implements UserDetailsService {
    @Reference
    AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        该局传过来的名字查询是否有这个admin账号
        System.out.println("username"+s);
        Admin admin = adminService.getAdminByName(s);
        if(null==admin){
            throw new UsernameNotFoundException("账号不存在");
        }
        //奇怪返回的是一个use

        return new User(admin.getUsername(),admin.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(""));

    }
}

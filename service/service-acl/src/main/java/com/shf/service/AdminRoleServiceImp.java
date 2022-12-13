package com.shf.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shf.dao.AdminDao;
import com.shf.dao.AdminRoleDao;
import com.shf.dao.BaseDao;
import com.shf.dao.RolaDao;
import com.shf.entity.Admin;
import com.shf.entity.AdminRole;
import com.shf.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = AdminRoleService.class)
public class AdminRoleServiceImp extends BaseSeriviceImp<AdminRole> implements AdminRoleService{
    @Autowired
    AdminRoleDao adminRoleDao;
    @Autowired
    RolaDao rolaDao;
    @Override
    protected BaseDao<AdminRole> getEntityDao() {
        return this.adminRoleDao;
    }

    @Override
    public Map<String, Object> findpageAdminRola(Long adminId) {
//        最主要的业务逻辑,把查询到的数据分为两类.
        List<Role> adminList = rolaDao.findAll();
        //获取admin中role的未删除的角色.
        List<Long> adminIdByRoleId = adminRoleDao.getAdminIdByRoleId(adminId);
//        设置两个集合,如果使用map的话,他就只有一个值,因为map会覆盖.使用list 就不会
        List<Role> assginRoleList=new ArrayList<>();
        List<Role> noAssginRoleList=new ArrayList<>();
        //遍历是否
        Map<String,Object> map = new HashMap();
        for (Role admin:adminList) {
            if(adminIdByRoleId.contains(admin.getId())){
              assginRoleList.add(admin);
            }else{
               noAssginRoleList.add(admin);
            }

        }
        map.put("assginRoleList",assginRoleList);
        map.put("noAssginRoleList",noAssginRoleList);
        return map;
    }
   //保存用户管理
    @Override
    public void findAssignRole(Long adminId, Long[] roleIds) {
//        先删除之前的然后再新增
        adminRoleDao.deleteByadminId(adminId);
        for (Long roleid:roleIds) {
            if(null!=roleid) {
                adminRoleDao.insertIsandRoleId(adminId, roleid);
            }
        }
    }
}

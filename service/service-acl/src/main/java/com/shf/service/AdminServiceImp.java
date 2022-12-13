package com.shf.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shf.dao.AdminDao;
import com.shf.dao.BaseDao;
import com.shf.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

@Service(interfaceClass = AdminService.class)
@Transactional
public class AdminServiceImp extends BaseSeriviceImp<Admin> implements AdminService{
    @Autowired
    @Resource
    public AdminDao adminDao;



    @Override
    protected BaseDao<Admin> getEntityDao() {
        return this.adminDao;
    }


    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    @Override
    public Admin getAdminByName(String username) {
        return adminDao.getAdminByName(username);
    }
}

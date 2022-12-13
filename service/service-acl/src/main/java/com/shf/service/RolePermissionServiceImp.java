package com.shf.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shf.dao.BaseDao;
import com.shf.dao.RolePermissionDao;
import com.shf.entity.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = RolePermissionService.class)
@Transactional
public class RolePermissionServiceImp extends BaseSeriviceImp<RolePermission> implements RolePermissionService {
    @Autowired
    RolePermissionDao rolePermissionDao;
    @Override
    protected BaseDao<RolePermission> getEntityDao() {
        return this.rolePermissionDao;
    }



}

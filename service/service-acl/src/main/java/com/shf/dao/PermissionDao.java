package com.shf.dao;

import com.shf.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionDao extends BaseDao<Permission>{
    List<Permission> findAll();

    List<Permission> findpermissionByAdminId(@Param("adminId") Long id);
}

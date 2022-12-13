package com.shf.dao;

import com.shf.entity.Admin;

import java.util.List;

public interface AdminDao extends BaseDao<Admin> {


    List<Admin> findAll();

    Admin getAdminByName(String username);
}

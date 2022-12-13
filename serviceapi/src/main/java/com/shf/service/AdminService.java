package com.shf.service;

import com.shf.entity.Admin;

import java.util.List;

public interface AdminService extends BaseService<Admin>{
    List<Admin> findAll();

    Admin getAdminByName(String username);
}

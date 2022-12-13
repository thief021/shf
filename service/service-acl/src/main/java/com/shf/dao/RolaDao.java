package com.shf.dao;

import com.shf.entity.Role;

import java.util.List;

public interface RolaDao extends BaseDao<Role> {
    List<Role> findAll();

//    Integer insert(Role role);
//
//    Integer update(Long id);
//
//     Role getById(Long id);
//
//    void update1(Role role);
//
//    List<Role> findPage(Map<String, Object> filters);
}

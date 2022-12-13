package com.shf.service;

import com.shf.entity.Role;

import java.util.List;

public interface RolaService extends BaseService<Role> {
    List<Role> findAll();
}

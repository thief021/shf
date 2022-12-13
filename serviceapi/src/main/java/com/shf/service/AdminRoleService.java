package com.shf.service;

import com.shf.entity.AdminRole;

import java.util.Map;

public interface AdminRoleService extends BaseService<AdminRole>{
    Map<String, Object> findpageAdminRola(Long adminId);

    void findAssignRole(Long adminId, Long[] roleIds);
}

package com.shf.service;

import com.shf.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService extends BaseService<Permission>{
    List<Map<String, Object>> findListbyLongId(Long id);

    void assignPermission(Long roleId, Long[] permissionIds);

    List<Permission> findpermissionByAdminId(Long id);
}

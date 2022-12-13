package com.shf.dao;

import com.shf.entity.Permission;
import com.shf.entity.RolePermission;

import java.util.List;

public interface RolePermissionDao extends BaseDao<RolePermission>{
    List<Long> findListByroleId(Long id);

    void deleteByroleId(Long roleId);

    void insetByroleIdandPId(Long roleId, Long permissionId);
}

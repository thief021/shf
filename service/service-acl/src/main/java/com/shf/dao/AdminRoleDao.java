package com.shf.dao;

import com.shf.entity.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleDao extends BaseDao<AdminRole>{
   List<Long> getAdminIdByRoleId(Long adminId);

    void insertIsandRoleId(@Param("adminid")Long adminId,@Param("roleid") Long roleid);

    void deleteByadminId(Long adminId);
}

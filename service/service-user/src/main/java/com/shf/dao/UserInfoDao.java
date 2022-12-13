package com.shf.dao;

import com.shf.entity.UserInfo;
import com.shf.service.BaseService;

public interface UserInfoDao extends BaseDao<UserInfo> {
    UserInfo getIdByPhone(String phone);
}

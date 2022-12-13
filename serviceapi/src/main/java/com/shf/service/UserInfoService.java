package com.shf.service;

import com.shf.entity.UserInfo;

public interface UserInfoService extends BaseService<UserInfo>{
    UserInfo getIdByPhone(String phone);
}

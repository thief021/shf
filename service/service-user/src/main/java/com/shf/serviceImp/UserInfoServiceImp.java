package com.shf.serviceImp;

import com.alibaba.dubbo.config.annotation.Service;
import com.shf.dao.BaseDao;
import com.shf.dao.UserInfoDao;
import com.shf.entity.UserInfo;
import com.shf.service.BaseSeriviceImp;
import com.shf.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Service(interfaceClass = UserInfoService.class)
@Controller
public class UserInfoServiceImp extends BaseSeriviceImp<UserInfo> implements UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;
    @Override
    protected BaseDao getEntityDao() {
        return this.userInfoDao;
    }


    @Override
    public UserInfo getIdByPhone(String phone) {
        return userInfoDao.getIdByPhone(phone);
    }
}

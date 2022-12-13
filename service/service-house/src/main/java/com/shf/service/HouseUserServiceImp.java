package com.shf.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shf.dao.BaseDao;
import com.shf.dao.HouseUserDao;
import com.shf.entity.HouseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service(interfaceClass = HouseUserService.class)
@Transactional
public class HouseUserServiceImp extends BaseSeriviceImp<HouseUser> implements HouseUserService{
    @Autowired
    HouseUserDao houseUserDao;
    @Override
    protected BaseDao<HouseUser> getEntityDao() {
        return this.houseUserDao;
    }

    @Override
    public List<HouseUser> findListByHouseId(Long houseId) {
        return houseUserDao.findListByHouseId(houseId);
    }
}

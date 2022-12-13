package com.shf.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shf.dao.BaseDao;
import com.shf.dao.HouseBrokerDao;
import com.shf.entity.HouseBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service(interfaceClass = HouseBrokerService.class)
@Transactional
public class HouseBrokerServiceImp extends BaseSeriviceImp<HouseBroker> implements HouseBrokerService {
    @Autowired
    HouseBrokerDao houseBrokerDao;
    @Override
    protected BaseDao<HouseBroker> getEntityDao() {
        return this.houseBrokerDao;
    }

    @Override
    public List<HouseBroker> findListByHouseId(Long houseId) {
        return houseBrokerDao.findListByHouseId(houseId);
    }
}

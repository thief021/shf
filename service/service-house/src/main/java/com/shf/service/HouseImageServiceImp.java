package com.shf.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shf.dao.BaseDao;
import com.shf.dao.HouseImageDao;
import com.shf.entity.HouseImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service(interfaceClass = HouseImageService.class)
@Transactional
public class HouseImageServiceImp extends BaseSeriviceImp<HouseImage> implements HouseImageService{
    @Autowired
    HouseImageDao houseImageDao;
    @Override
    protected BaseDao<HouseImage> getEntityDao() {
        return this.houseImageDao;
    }

    @Override
    public List<HouseImage> findList(Long houseId, Integer type) {
        return houseImageDao.findList(houseId,type);
    }
}

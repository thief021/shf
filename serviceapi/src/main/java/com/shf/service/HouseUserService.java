package com.shf.service;

import com.shf.entity.HouseUser;

import java.util.List;

public interface HouseUserService extends BaseService<HouseUser>{
    List<HouseUser> findListByHouseId(Long houseId);
}



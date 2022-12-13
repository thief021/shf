package com.shf.dao;

import com.shf.entity.HouseUser;

import java.util.List;

public interface HouseUserDao extends BaseDao<HouseUser>{
    List<HouseUser> findListByHouseId(Long houseId);
}

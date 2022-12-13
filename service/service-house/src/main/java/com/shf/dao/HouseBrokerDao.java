package com.shf.dao;

import com.shf.entity.HouseBroker;

import java.util.List;

public interface HouseBrokerDao  extends BaseDao<HouseBroker>{
    List<HouseBroker> findListByHouseId(Long houseId);
}

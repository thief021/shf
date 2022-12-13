package com.shf.service;

import com.shf.entity.HouseBroker;

import java.util.List;

public interface HouseBrokerService extends BaseService<HouseBroker>{
    List<HouseBroker> findListByHouseId(Long houseId);
}

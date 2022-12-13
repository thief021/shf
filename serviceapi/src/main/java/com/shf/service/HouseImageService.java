package com.shf.service;

import com.shf.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseImageService extends BaseService<HouseImage>{
    List<HouseImage> findList( Long houseId,  Integer type);
}

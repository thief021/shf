package com.shf.service;
import com.github.pagehelper.PageInfo;
import com.shf.entity.House;
import com.shf.vo.HouseQueryVo;
import com.shf.vo.HouseVo;

public interface HouseService extends BaseService<House>{
    void publish(Long id, Integer status);

    PageInfo<HouseVo> findpageList(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo);
}

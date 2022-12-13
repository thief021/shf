package com.shf.dao;

import com.github.pagehelper.Page;
import com.shf.entity.House;
import com.shf.vo.HouseQueryVo;
import com.shf.vo.HouseVo;
import org.apache.ibatis.annotations.Param;

public interface HouseDao extends BaseDao<House>{


    Page<HouseVo> findPageList(@Param("vo") HouseQueryVo houseQueryVo);
//    Page<HouseVo> findPageList2(@Param("houseQueryVo")HouseQueryVo houseQueryVo);
}

package com.shf.service;

import com.alibaba.dubbo.config.annotation.Service;

//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.Page;
import com.shf.dao.BaseDao;
import com.shf.dao.DictDao;
import com.shf.dao.HouseDao;
import com.shf.entity.House;
import com.shf.vo.HouseQueryVo;
import com.shf.vo.HouseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service(interfaceClass = HouseService.class)
@Transactional
public class hoseServiceImpl extends BaseSeriviceImp<House> implements HouseService {
    @Autowired
    HouseDao houseDao;
    @Autowired
    DictDao dictDao;
    @Override
    protected BaseDao<House> getEntityDao() {
        return  this.houseDao;
    }

    @Override
    public House getById(Serializable id) {
        //目的是为了把查询的数字替换成dict中的名字
//        根据ID查询到house对象
        House house = houseDao.getById(id);
//        根据house对象的状态码找到对应的文字

        String nameById = dictDao.getNameById(house.getHouseTypeId());
        String nameById1 = dictDao.getNameById(house.getFloorId());
        String nameById2 = dictDao.getNameById(house.getDirectionId());
        String nameById3 = dictDao.getNameById(house.getBuildStructureId());
        String nameById4 = dictDao.getNameById(house.getDecorationId());
        String nameById5 = dictDao.getNameById(house.getHouseUseId());
//        注入到查询到的house对象中
        house.setHouseTypeName(nameById);
        house.setFloorName(nameById1);
        house.setDirectionName(nameById2);
        house.setBuildStructureName(nameById3);
        house.setDecorationName(nameById4);
        house.setHouseUseName(nameById5);
        return house;



    }

    @Override
    public void publish(Long id, Integer status) {
        //新建一个house对象
        House house = new House();
//        设置id
        house.setId(id);
//        设置状态码
        house.setStatus(status);
//        把house对象传入到更新的操作中
        houseDao.update(house);


    }

    @Override
    public PageInfo<HouseVo> findpageList(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo) {
        PageHelper.startPage(pageNum,5);
//        houseQueryVo.setKeyword("test");
//        Page<HouseVo> pageList2 = houseDao.findPageList2(houseQueryVo);
//        for (HouseVo houseVo : pageList2) {
////            System.out.println(houseVo.toString());
//        }
        Page<HouseVo> pageList = houseDao.findPageList(houseQueryVo);
        for (HouseVo h: pageList) {
            //循环遍历page把得到的page中未取得的数据设置一下.
            String nameById = dictDao.getNameById(h.getFloorId());
            //得到
            String nameById1 = dictDao.getNameById(h.getHouseTypeId());
            String nameById2 = dictDao.getNameById(h.getDirectionId());
            h.setFloorName(nameById);
            h.setHouseTypeName(nameById1);
            h.setDirectionName(nameById2);



        }
        return new PageInfo<HouseVo>(pageList,5);
    }
}

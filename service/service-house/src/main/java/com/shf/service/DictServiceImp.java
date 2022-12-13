package com.shf.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shf.dao.DictDao;
import com.shf.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service(interfaceClass = DictService.class )
@Transactional
public class DictServiceImp implements DictService{
    @Autowired
    DictDao dictDao;

    @Override
    public List<Map<String, Object>> findZnodes(Long id) {
//        用来接受传过去的数据
        List<Map<String, Object>> Znodes= new ArrayList<>();
//        查询所有数据
        List<Dict> all =dictDao.findZnodes(id);
        //把数据传进去
        for (Dict dict:all) {
            Map map=new HashMap();
            map.put("id",dict.getId());
            map.put("name",dict.getName());
//            根据ID查询子节点的个数
            Integer integer= dictDao.isParent(dict.getId());
            map.put("isParent",integer > 0 ?true: false);

           Znodes.add(map);
        }

        return Znodes ;
    }

    @Override
    public List<Dict> findListByParentId(Long parentId) {
        return dictDao.findListByParentId(parentId);
    }

    @Override
    public List<Dict> findListByDictCode(String dictCode) {
        Dict dict = dictDao.getByDictCode(dictCode);
        if(null == dict) return null;
        return this.findListByParentId(dict.getId());
    }

    @Override
    public String getNameById(Long floorId) {
        String nameById = dictDao.getNameById(floorId);
        return nameById;
    }

}

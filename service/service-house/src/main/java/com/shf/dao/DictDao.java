package com.shf.dao;

import com.shf.entity.Dict;

import java.util.List;

public interface DictDao {
    public List<Dict> findZnodes( Long id);
    public Integer isParent(Long id) ;

    List<Dict> findListByParentId(Long parentId);

    Dict getByDictCode(String dictCode);

    String getNameById(Long areaId);
}

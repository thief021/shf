package com.shf.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shf.dao.BaseDao;
import com.shf.dao.RolaDao;
import com.shf.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

@Service(interfaceClass = RolaService.class)
@Transactional
public class RolaServiceImp extends BaseSeriviceImp<Role> implements RolaService {
    @Autowired
    @Resource
    public RolaDao rolaDao;


    @Override
    public List<Role> findAll() {
        return rolaDao.findAll();
    }

    @Override
    protected BaseDao<Role> getEntityDao() {
        return rolaDao;
    }



//    public void insert(Role role) {
//        rolaDao.insert(role);
//    }
//
//    public void delete(Long id) {
//        rolaDao.delete(id);
//    }
//
//    public Role getById(Long id) {
//      return rolaDao.getById(id);
//    }
//
//    public void update(Role role) {
//        rolaDao.update(role);
//    }
//
//    public PageInfo<Role> findPage(Map<String, Object> filters) {
//        //当前页数
//        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
//        //每页显示的记录条数
//        int pageSize = CastUtil.castInt(filters.get("pageSize"), 10);
//
//        PageHelper.startPage(pageNum, pageSize);
//        return new PageInfo<Role>(rolaDao.findPage(filters), 10);
//    }
}

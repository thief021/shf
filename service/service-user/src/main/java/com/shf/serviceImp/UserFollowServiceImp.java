package com.shf.serviceImp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shf.controller.BaseController;
import com.shf.dao.BaseDao;
import com.shf.dao.UserFollowDao;
import com.shf.entity.UserFollow;
import com.shf.service.*;
import com.shf.vo.UserFollowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass =UserFollowService.class )
@Transactional
public class UserFollowServiceImp extends BaseSeriviceImp<UserFollow> implements UserFollowService {
    @Autowired
    UserFollowDao userFollowDao;
    @Reference
    DictService dictService;
    @Override
    protected BaseDao<UserFollow> getEntityDao() {
        return this.userFollowDao;
    }

    @Override
    public void follow(Long id, Long id1) {
        //不调用follow方法,只是新增了一个usefollow对象
        UserFollow userFollow = new UserFollow();
        userFollow.setUserId(id);
        userFollow.setHouseId(id1);
        userFollowDao.insert(userFollow);

    }

    @Override
    public PageInfo<UserFollowVo> findPageList(Integer pagenum, Integer pagesize, Long id) {
        //开启分页
        PageHelper.startPage(pagenum,pagesize);
        //查询所有的数据
        Page<UserFollowVo> page=userFollowDao.findpageList(id);
        //把数字替换成字符串
        for (UserFollowVo use:page) {
           String foolrNmae= dictService.getNameById(use.getFloorId());
            String dictServiceNameById = dictService.getNameById(use.getDirectionId());
            String HOUSE = dictService.getNameById(use.getHouseTypeId());
            use.setHouseTypeName(HOUSE);
            use.setDirectionName(dictServiceNameById);
            use.setFloorName(foolrNmae);

        }
        return new PageInfo<>(page,5);

    }
}

package com.shf.service;

import com.github.pagehelper.PageInfo;
import com.shf.entity.UserFollow;
import com.shf.vo.UserFollowVo;

public interface UserFollowService extends BaseService<UserFollow> {
    void follow(Long id, Long id1);

    PageInfo<UserFollowVo> findPageList(Integer pagenum, Integer pagesize, Long id);
}

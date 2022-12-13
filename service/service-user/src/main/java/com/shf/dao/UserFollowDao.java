package com.shf.dao;

import com.github.pagehelper.Page;
import com.shf.entity.UserFollow;
import com.shf.vo.UserFollowVo;

public interface UserFollowDao extends BaseDao<UserFollow>{
    Page<UserFollowVo> findpageList(Long id);
}

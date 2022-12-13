package com.shf.dao;

import com.shf.entity.Community;

import java.util.List;

public interface CommunityDao extends BaseDao<Community>{
    List<Community> findAll();
}

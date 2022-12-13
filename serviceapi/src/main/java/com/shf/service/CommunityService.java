package com.shf.service;

import com.github.pagehelper.PageInfo;
import com.shf.entity.Community;
import com.shf.entity.Role;

import java.util.List;
import java.util.Map;

public interface CommunityService extends BaseService<Community> {
    List<Community> findAll();
}


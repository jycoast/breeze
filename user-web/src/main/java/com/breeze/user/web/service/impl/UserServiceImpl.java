package com.breeze.user.web.service.impl;

import com.breeze.user.web.entity.User;
import com.breeze.user.web.mapper.UserMapper;
import com.breeze.user.web.service.UserService;

import javax.annotation.Resource;

public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }
}

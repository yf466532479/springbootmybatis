package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.User;
import com.example.springbootmybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> queryInfo(String id) {
        List<User> list = userMapper.queryInfo(id);
        return list;
    }

    @Override
    public Boolean insert(User user) {
        Boolean insert = userMapper.insert(user);
        return insert;
    }
}

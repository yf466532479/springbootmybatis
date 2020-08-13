package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserService {

    List<User> queryInfo(String id);
    Boolean insert(User user);
}

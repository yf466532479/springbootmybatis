package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserService {

    List<User> queryInfo(String id);

    List<User> queryall() throws ExecutionException, InterruptedException;

    Boolean insert(User user);
}

package com.example.springbootmybatis.mapper;


import com.example.springbootmybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper

public interface UserMapper {

    List<User> queryInfo(String id);
    Boolean insert(@Param("user") User user);

}

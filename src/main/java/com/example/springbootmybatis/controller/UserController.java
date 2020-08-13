package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.common.CheckRepeatData;
import com.example.springbootmybatis.entity.User;
import com.example.springbootmybatis.service.UserService;
import com.oracle.tools.packager.Log;
import com.sun.tools.javac.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/yff")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    //保证幂等性的一种方法， 查询方法本身就是幂等的
    private Map<String,Object> map = new HashMap<>();
    @PostMapping("/queryinfo")
    public Object queryInfo(@RequestBody User user){

        String id = String.valueOf(user.getId());
        if(StringUtils.isEmpty(id)){
            return "输入查询参数";
        }
        List<User> list = userService.queryInfo(id);
        return list;
    }

    @PostMapping("/insert")
    public Object insert(@RequestBody User user){

        boolean b = map.containsKey(user.getName());
        if(b){
            return "新增重复";
        }else{
            map.put(user.getName(),user.getName());
        }
//        CheckRepeatData.ins().CheckRepeatData(new ArrayList<>());
        return userService.insert(user);

    }


}

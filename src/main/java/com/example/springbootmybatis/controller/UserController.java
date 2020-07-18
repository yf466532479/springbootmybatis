package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.entity.User;
import com.example.springbootmybatis.service.UserService;
import com.oracle.tools.packager.Log;
import com.sun.tools.javac.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/yff")
public class UserController {

    @Autowired
    UserService userService;
    //保证幂等性的一种方法， 查询方法本身就是幂等的，但是不想写 insert方法了 所以就写在一个方法里了
    private Map<String,Object> map = new HashMap<>();
    @PostMapping("/queryinfo")
    public Object queryInfo(@RequestBody User user){

        String id = user.getId();
        if(StringUtils.isEmpty(id)){
            return "输入查询参数";
        }
        boolean b = map.containsKey(id);
        if(b){
            return "我只准你查询一遍";
        }else{
            map.put(id,id);
        }
        List<User> list = userService.queryInfo(id);
        return list;
    }

}

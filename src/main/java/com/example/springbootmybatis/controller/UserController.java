package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.annotations.CheckRepeatSubmit;
import com.example.springbootmybatis.aop.HttpLister;
import com.example.springbootmybatis.entity.User;
import com.example.springbootmybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/yff")
@Slf4j
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    //保证幂等性的一种方法， 查询方法本身就是幂等的
    private Map<String,Object> map = new HashMap<>();


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    /**
     * 登录
     */
    @PostMapping("/login")
    public void getUserByUserNameAndPassword(@RequestBody User user, HttpSession  session) {

        String id = String.valueOf(user.getId());

        logger.info("用户【"+id+"】登陆开始！");
        session.setAttribute(id,id);
        logger.info("用户【"+id+"】登陆成功！");
    }
    /**
     *查询在线人数
     */
    @RequestMapping("/online")
    public Object online() {
        return  "当前在线人数：" + HttpLister.online + "人";
    }
    /**
     * 退出登录
     */
    @RequestMapping("/out")
    public Object Logout(@RequestBody User user, HttpServletRequest request) {
        logger.info("用户退出登录开始！");
        HttpSession session = request.getSession(false);//防止创建Session
        if(session != null){
            session.removeAttribute(String.valueOf(user.getId()));
            session.invalidate();
        }
        logger.info("用户退出登录结束！{}",user.getId());
        return  "退出成功！";
    }





    @CheckRepeatSubmit
    @PostMapping("/queryinfo")
    public Object queryInfo(@RequestBody User user){

        String id = String.valueOf(user.getId());
        if(StringUtils.isEmpty(id)){
            return "输入查询参数";
        }
        List<User> list = userService.queryInfo(id);
        return list;
    }


    @PostMapping("/queryall")
    public Object queryall(@RequestBody User user) throws ExecutionException, InterruptedException {

//        String id = String.valueOf(user.getId());
//        if(StringUtils.isEmpty(id)){
//            return "输入查询参数";
//        }
        List<User> list = userService.queryall();
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

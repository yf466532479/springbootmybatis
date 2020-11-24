package com.example.springbootmybatis.aop;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听器
 * httpSessionListener来实如今线人数的统计
 * 一个浏览器只会被记录一个session  如果用postman 测试可以修改 sessionid 达到模拟的效果
 * 还有就是记录错误的原因 可以参考 https://www.cnblogs.com/fangh816/p/13489759.html
 *
 * Springboot 记得在 appman 那添加注解 @ServletComponentScan 才能被注册bean
 *
 */
@WebListener
@Slf4j
public class HttpLister implements HttpSessionListener {

    public static int online = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        online++;
        log.info("又有人上线了，统计一下总人数吧！{}",online);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        online--;
        log.info("又有人下线了，统计一下总人数吧！{}",online);
    }
}

package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.User;
import com.example.springbootmybatis.mapper.UserMapper;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private static LinkedBlockingQueue<User> CACHE_QUEUE = new LinkedBlockingQueue();

    ThreadPoolExecutor executor = new  ThreadPoolExecutor(3, 6, 3,
                                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> queryall()  {
        List<User> list = userMapper.queryall();
        List<User> result = new ArrayList<>();
        CACHE_QUEUE.addAll(list);
        int size = CACHE_QUEUE.size();

         Future<List<User>> submit= executor.submit(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                while (size != 0) {
                    User take = CACHE_QUEUE.take();
                    System.out.println("每次取值=="+take.toString());
                    result.add(take);
                }
                result.add(new User(2312223, "1", "2", "3"));
                return result;
            }
        });

        while (result.size() != size){
            System.out.println("？？？？？");

            continue;
        }
        return result;

//        boolean done = false;
//        while (!done){
//             done = submit.isDone();
//        }
//
//        try {
//            List<User> users = submit.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }


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

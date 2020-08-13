package com.example.springbootmybatis.common;


import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("CheckRepeatData")
public class CheckRepeatData {
    private static CheckRepeatData ins;
    static {
        ins = new CheckRepeatData();
    }
    private CheckRepeatData(){};

    public static CheckRepeatData ins(){
        return ins;
    }

    public Object CheckRepeatData(List<Map<String, Object>> map){
        List<String> list = new ArrayList<>();

        //将要作为map的值取出来放进list 查看有木有 重复的值在业务上碰到了这种情况。
        for (Map<String, Object> key : map) {
            key.forEach((k,v)->{
                list.add(k);
            });
//            Set<Map.Entry<String, Object>> set = key.entrySet();
//            for (Map.Entry<String, Object> stringObjectEntry : set) {
//                String key1 = stringObjectEntry.getKey();
//                list.add(key1);
//            }
        }


        System.out.println(list.toString());
        Map<String, Long> collect = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("111==="+collect.toString());
        return collect;
    }

    public static void main(String[] args) {
        List<Map<String, Object>> list= new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("q"+i,"a"+i);
            list.add(map);
        }
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("q"+i,"a"+i);
            list.add(map);
        }
        CheckRepeatData.ins.CheckRepeatData(list);
    }




}

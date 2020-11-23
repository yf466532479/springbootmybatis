package com.example.springbootmybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    /**
     * 设置 id 为 int  可以在 mybatis 中使用 自增，但是在建表时候要对id 进行勾选自增
     */
    private int id;

    private String name;

    private String age;

    private String sex;

}

package com.exam.wy.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname User
 * @Author wangyun
 * @Description TODO
 * @Date 2019/7/17 19:29
 */
@Data
public class User implements Serializable {
    private String id;
    private String name;
    private Integer age;

    public static String keyName(){
        return "user:";
    }
}

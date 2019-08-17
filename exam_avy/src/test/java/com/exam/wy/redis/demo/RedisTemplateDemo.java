package com.exam.wy.redis.demo;

import com.exam.wy.bean.User;
import com.exam.wy.redis.springdate.RedisServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Classname RedisTemplateDemo
 * @Author wangyun
 * @Description TODO
 * @Date 2019/7/17 21:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring_core.xml","classpath:spring_redis.xml"})
public class RedisTemplateDemo {
    @Autowired
    private RedisServiceImpl redisService;
    @Test
    public void t1String(){
        String result=redisService.selectGnameById("1905");
        System.out.println(result);
    }
    @Test
    public void t2Hash(){
        User user=redisService.selectUserById("333");
        System.out.println(user);
    }
    @Test
    public void t3List(){
        //初始化物流的时间
        String key="1001";
       // redisService.init(key);
        List<String> list1=redisService.lately(key);
        System.out.println("=============初始化需要执行的任务是:==================");
        for(String s:list1){
            System.out.println(s);
        }
        //快递小哥扫描,当前在执行任务
        String suc=redisService.touch(key);
        System.out.println("=============当前正在执行的任务是:==================");
        System.out.println(suc);

        List<String> list12=redisService.lately(key);
        System.out.println("=============需要执行的任务是:==================");
        for(String s:list12){
            System.out.println(s);
        }
        //客户查看物流
        List<String> list2=redisService.checkdByUser(key);
        System.out.println("=============物流信息是:==================");
        for(String s: list2){
            System.out.println(s);
        }
    }


}

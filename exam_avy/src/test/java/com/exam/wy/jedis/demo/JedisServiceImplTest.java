package com.exam.wy.jedis.demo;

import com.exam.wy.bean.User;
import com.exam.wy.redis.jedis.JedisServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Classname JedisServiceImplTest
 * @Author wangyun
 * @Description TODO
 * @Date 2019/7/17 20:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring_core.xml"})
public class JedisServiceImplTest {
    @Autowired
    private JedisServiceImpl jedisService;
    @Test
    public void t1(){
        String result=jedisService.selectGnameById("123");
        System.out.println(result);
    }
    @Test
    public void t2(){
        User user=jedisService.selectUserById("123");
        System.out.println(user);
    }

}

package com.exam.wy;

import com.exam.wy.service.Impl.LimitLoginServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Classname LimitLoginServiceImplDemo
 * @Author wangyun
 * @Description TODO
 * @Date 2019/7/18 0:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring_core.xml","classpath:spring_redis.xml"})
public class LimitLoginServiceImplDemo {
    @Autowired
    private LimitLoginServiceImpl limitLoginService;
    @Test
    public void t1(){
        String result=limitLoginService.limitLogin("avy","1234");
        System.out.println(result);
    }

}

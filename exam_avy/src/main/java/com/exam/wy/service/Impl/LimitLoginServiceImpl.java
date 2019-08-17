package com.exam.wy.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Classname LimitLoginServiceImpl
 * @Author wangyun
 * @Description TODO
 * @Date 2019/7/17 23:15
 */
@Service
public class LimitLoginServiceImpl {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Resource(name="redisTemplate")
    private ValueOperations<String, String> string;


    public String limitLogin(String uname,String upass) {

        String countKey = "user:loginCount:" + uname;
        String realKey = "uname:upass:" + uname;
        if (!redisTemplate.hasKey(realKey)) {
            return "用户名不存在";
        }else{
            //判断用户是否被限制登录  key是:"user:loginLock:"+uname  是否存在
            String lockKey = "user:loginLock:" + uname;
            if (redisTemplate.hasKey(lockKey)) {  //被限制登录
                //获得限制时间
                Long lockTime = redisTemplate.getExpire(lockKey, TimeUnit.MINUTES);
                return "您已被限制登录,您还剩" + lockTime + "分钟可以再次登录";
            } //没有被限制登录
            //判断密码是否错误
            //获得Redis中对应的密码
            String realPass=redisTemplate.opsForValue().get(realKey);
            if(realPass.equals(upass)) {    //密码正确
                //判断之前有没有错误登录过
                if (redisTemplate.hasKey(countKey)) {   //不是是首次失败登录
                    redisTemplate.delete(countKey);   //将错误的key删除
                    return "登录成功";
                }
                    return "登录成功";
            }else {
                //判断是否是首次失败登录    登录错误的key为  "user:loginCount:"+uname
                if (!redisTemplate.hasKey(countKey)) {   //是首次失败登录
                    //将countKey的value值设为1
                    string.set(countKey, "1");
                    //再将失效时间设为2分钟
                    redisTemplate.expire(countKey, 2, TimeUnit.MINUTES);
                    //提示用户登录失败,和剩余登录次数
                    return "此次登录失败,您还有4次机会";
                } else {    //不是第一次错误登录
                    //查询登录失败的次数
                    Long count = Long.parseLong(string.get(countKey));
                    //判断失败次数是否>=5
                    if (count < 4) {    //错误次数小于5
                        //给错误次数+1
                        string.increment(countKey, 1);   //对指定的key增加指定的数据
                        //获得过期剩余时间
                        Long time = redisTemplate.getExpire(countKey, TimeUnit.SECONDS);
                        return "此次登录失败,在" + time + "秒内,您还可以登录" + (4 - count) + "次";
                    } else {    //错误次数大于5
                        //限制用户登录
                        string.set(lockKey, "1");
                        //设置过期时间
                        redisTemplate.expire(lockKey, 1, TimeUnit.HOURS);
                        return "您已被限制登录,1小时后可以重新登录";
                    }
                }
            }
        }
    }
}

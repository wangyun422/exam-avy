package com.exam.wy.redis.jedis;

import com.exam.wy.bean.User;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @Classname JedisServiceImpl
 * @Author wangyun
 * @Description TODO
 * @Date 2019/7/17 20:26
 */
@Service
public class JedisServiceImpl {

    /**
     * 判断字符串基本操作
     */
    public String selectGnameById(String id){
        String key="grade:"+id+":name";
        Jedis jedis=JedisUtil.getJedis();
        if(jedis.exists(key)){
            System.out.println("Redis中查询出来的");
            return jedis.get(key);
        }else {  //数据库中查询出来
            String result = "Java1901";
            System.out.println("数据库中查询出来");
            jedis.set(key, result);
            return result;
        }
    }

    public User selectUserById(String id){

        //连接本地的 Redis 服务   Redis的命令和Jedis的命令是一一对应的
        Jedis jedis=JedisUtil.getJedis();
        String key=User.keyName()+id;
        if(jedis.exists(key)){
            Map<String,String> map=jedis.hgetAll(key);
            User user=new User();
            user.setAge(Integer.parseInt(map.get("age")));
            user.setName(map.get("name"));
            user.setId(map.get("id"));
            System.out.println("从Redis中进行查询返回的对象是:"+user);
            return user;
        }else {
            //如果不存在查询数据库
            User user = new User();
            user.setId(id);
            user.setName("美丽");
            user.setAge(22);
            jedis.hset(key, "name", user.getName());
            jedis.hset(key, "age", user.getAge() + "");
            jedis.hset(key, "id", user.getId());
            System.out.println("数据库进行查询操作返回的对象是:" + user);
            return user;
        }
    }
}

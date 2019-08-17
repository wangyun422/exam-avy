package com.exam.wy.redis.springdate;

import com.exam.wy.bean.User;
import com.exam.wy.redis.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Classname RedisServiceImpl
 * @Author wangyun
 * @Description TODO
 * @Date 2019/7/17 21:39
 */
@Service
public class RedisServiceImpl {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Resource(name="redisTemplate")
    private ValueOperations<String, String> string;
    @Resource(name="redisTemplate")
    private  ListOperations<String, String> list;



    /**
     *K  实体bean定义:  user/ user:
     * HK  在这儿是主键类型
     * HV  valuel类型   用Object还要强转,在底下
     */
    @Resource(name="redisTemplate")
    HashOperations<String, String, User>  hash;

    /**String存取操作
     *
     */
    public String selectGnameById(String id){
        String key="grade:"+id+":name";
        if(redisTemplate.hasKey(key)){
            System.out.println("Redis中查询出来的");
           // return redisTemplate.opsForValue().get(key);
            return string.get(key);
        }else {  //数据库中查询出来
            String result = "Java1901";
            System.out.println("数据库中查询出来");
            //redisTemplate.opsForValue().set(key,result);
            string.set(key,result);
            return result;
        }
    }

    /**
     * hash存取操作
     */
    public User selectUserById(String id){
        if(hash.hasKey("users:",id)){

            System.out.println("从Redis中进行查询返回的对象是:");
            return hash.get("users:",id);
        }else {
            //如果不存在查询数据库
            User user = new User();
            user.setId(id);
            user.setName("小黑");
            user.setAge(22);
            hash.put("users:",id,user);
            System.out.println("数据库进行查询操作返回的对象是:" + user);
            return user;
        }
    }

    /**
     * list操作
     */
    //用户下单后,生成任务流程
    public void init(String cardId){
        String runKey ="products:"+cardId;   //初始任务对列
        Long allNews=list.leftPushAll(runKey,"商家发货", "快递小哥收货", "发送北京首都机场",
                "北京-->南京禄口机场","南京机场-->建邺","建邺区X快递点","本人已收货");
    }
    //小哥触发事件
    public String touch(String cardId){
        String runKey ="products:"+cardId;   //需要执行的任务
        String sucKey ="products:success:"+cardId;   //成功执行的任务
        return list.rightPopAndLeftPush(runKey,sucKey);
    }
    //当前需要执行的任务
    public List<String> lately(String cardId){
        String runKey ="products:"+cardId;
        return list.range(runKey,0,-1);
    }
    //用户查看物流信息
    public List<String> checkdByUser(String cardId){
        String sucKey ="products:success:"+cardId;
        return list.range(sucKey,0,-1);
    }
}

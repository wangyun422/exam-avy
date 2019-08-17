package com.exam.wy.jedis.demo;

import com.exam.wy.bean.User;
import com.exam.wy.redis.jedis.JedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * @Classname Demo
 * @Author wangyun
 * @Description TODO
 * @Date 2019/7/17 18:50
 */
public class Demo {

    /**
     * hash  模拟hash对一个对象的存和取的操作
     * 判断Redis中该key是否存在
     *        存在    直接查询取出并返回
     *        不存在   查询数据库,将数据库的数据读取出来,并存入Redis中,并返回
     */
    @Test
    public void hash(){
        //连接本地的 Redis 服务   Redis的命令和Jedis的命令是一一对应的
        Jedis jedis = new Jedis("192.168.184.130",6379);
        jedis.auth("redis");
        String id="1";
        String key="user:"+id;
        if(jedis.exists(key)){
           Map<String,String> map=jedis.hgetAll(key);
           User user=new User();
           user.setAge(Integer.parseInt(map.get("age")));
           user.setName(map.get("name"));
           user.setId(map.get("id"));
            System.out.println("从Redis中进行查询返回的对象是:"+user);
        }else{
            //如果不存在查询数据库
           User user= new Demo().selectById(id);
           jedis.hset(key,"name",user.getName());
           jedis.hset(key,"age",user.getAge()+"");
           jedis.hset(key,"id",user.getId());
            System.out.println("数据库进行查询操作返回的对象是:"+user);
        }
    }

    /**
     * 模拟数据库查询  service中
     * @param id
     * @return
     */
    public User selectById(String id){
        User user = new User();
        if(id=="1"){
            user.setAge(27);
            user.setName("美玲");
            user.setId("1");
        }else if(id=="2"){
            user.setName("坤坤");
            user.setAge(22);user.setId("2");
        }
        return user;
    }


    public static void main(String[] args) {

       // JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        /*config.setMaxTotal(10);
        config.setMinIdle(2);
        JedisPool jedisPool = new JedisPool(config,"192.168.184.130",6379);
        //连接本地的 Redis 服务   Redis的命令和Jedis的命令是一一对应的
        Jedis jedis = jedisPool.getResource();*/
        Jedis jedis= JedisUtil.getJedis();

        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());

        //模拟一个string类型的赋值和取值操作
        /**
         * 用户要做一个查询数据,查询班级名称
         * 先判断Redis是否存在该key
         *        存在  从Redis中查询并返回
         *        不存在  查询数据库,将值存入Redis中,再返回
         */

        String key="grade:name";
        if(jedis.exists(key)){
            String value = jedis.get(key);
            System.out.println("从Redis服务器中取出的:"+value);
        }else{
            System.out.println("在数据库中进行查询");
            String value="Java1901";
            jedis.set(key,value);
            System.out.println("结果返回:"+value);
        }
        jedis.close();
    }


}

package com.exam.wy.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Classname JedisUtil
 * @Author wangyun
 * @Description TODO
 * @Date 2019/7/17 20:19
 */
public class JedisUtil {

    private static JedisPool JEDIS_POOL=null;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(10);
        config.setMinIdle(2);
        JEDIS_POOL = new JedisPool(config,"192.168.184.130",6379,2000,"redis");
    }
    public static Jedis getJedis(){
        Jedis jedis = JEDIS_POOL.getResource();
        return jedis;
    }

    public static void closeJedis(Jedis jedis){
        jedis.close();
    }
}

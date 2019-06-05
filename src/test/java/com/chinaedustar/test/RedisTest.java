package com.chinaedustar.test;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import com.chinaedustar.app.achievement.common.constants.RedisConst;
import com.chinaedustar.common.redis.RedisCallBack;
import com.chinaedustar.common.redis.RedisUtils;

public class RedisTest {

    public static void testString() throws Exception {
        RedisUtils re = RedisUtils.instance(RedisConst.REDIS_TEST);
        String tt = re.execute(new RedisCallBack<String>() {

            @Override
            public String doInRedis(Jedis jedis) {
                Transaction transaction = jedis.multi();
                transaction.set("ttt", "dddd");
                Response<String> result1 = transaction.get("ttt");
                transaction.exec();
                return result1.get();
            }
        });
        System.out.println(tt);
    }

    public static void testMap() throws Exception {
        RedisUtils re = RedisUtils.instance(RedisConst.REDIS_TEST);
        Map<String, String> tt = re.execute(new RedisCallBack<Map<String, String>>() {

            @Override
            public Map<String, String> doInRedis(Jedis jedis) {
                Transaction transaction = jedis.multi();
                Map<String, String> map = new HashMap<String, String>();
                map.put("ttt", "ttttt");
                map.put("ccc", "ccccc");
                map.put("ddd", "ddddd");
                transaction.hmset("mapTest", map);
                Response<Map<String, String>> result1 = transaction.hgetAll("mapTest");
                transaction.exec();
                return result1.get();
            }
        });
        System.out.println(tt);
    }

    public static void main(String[] args) throws Exception {
        testMap();
    }
}

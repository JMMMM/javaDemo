package redis_lock;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisIncyDemo {

    public static void main(String[] args) throws InterruptedException {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);
        JedisPool jedisPool = new JedisPool(config, "192.168.3.248", 6379, 2000);

        Jedis jedis = jedisPool.getResource();
        jedis.del("ceshi");
        jedis.set("ceshi", "0");
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                Jedis j = jedisPool.getResource();
                j.incrBy("ceshi", 1L);
                j.close();
            }).start();
        }
        Thread.sleep(5 * 1000);
        System.out.println(jedis.get("ceshi"));
    }
}

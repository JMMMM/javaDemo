package redis_lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

public final class RedisLockHelper {
    private static Logger logger = LoggerFactory.getLogger(RedisLockHelper.class);
    private final JedisPool jedisPool = new JedisPool("192.168.4.224");

    public String lockWithTimeout(String lockName, long acquireTimeout, long timeout) {
        Jedis conn = jedisPool.getResource();
        String retIdentifier = null;
        String identifier = UUID.randomUUID().toString();
        String lockKey = "lock:" + lockName;
        int lockExpire = (int) (timeout / 1000);
        long end = System.currentTimeMillis() + acquireTimeout;
        while (System.currentTimeMillis() < end) {
            System.out.println(conn.eval("return KEYS[1]",3,lockKey,identifier,lockExpire+""));
            System.out.println(conn.eval("return KEYS[2]",3,lockKey,identifier,lockExpire+""));
            System.out.println(conn.eval("return KEYS[3]",3,lockKey,identifier,lockExpire+""));
            Object obj = conn.eval("local exists = redis.call('setnx',KEYS[1],KEYS[2]);redis.call('expire',KEYS[1],KEYS[3]);return exists",3,lockKey,identifier,lockExpire+"");
            if (1 == (long)obj) {
                retIdentifier = identifier;
                return retIdentifier;
            }
            if (conn.ttl(lockKey) == -1) {
                conn.expire(lockKey, lockExpire);
            }

        }
        return retIdentifier;
    }

    public boolean releaseLock(String lockName, String identifier) {
        Jedis conn = null;
        String lockKey = "lock:" + lockName;
        boolean retFlag = false;
        conn = jedisPool.getResource();
        while (true) {
            conn.watch(lockKey);
            if (identifier.equals(conn.get(lockKey))) {
                Transaction transaction = conn.multi();
                transaction.del(lockKey);
                List<Object> results = transaction.exec();
                if (results == null) continue;
                retFlag = true;
            }
            conn.unwatch();
            break;
        }
        return retFlag;
    }

    public static void main(String[] args) {
        String lock = new RedisLockHelper().lockWithTimeout("locklock",10*1000,10*1000);
        System.out.println(lock);
    }
}

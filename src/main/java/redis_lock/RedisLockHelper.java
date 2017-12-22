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
    private final JedisPool jedisPool = new JedisPool("192.168.99.100");

    public String lockWithTimeout(String localName, long acquireTimeout, long timeout) {
        Jedis conn = jedisPool.getResource();
        String retIdentifier = null;
        String identifier = UUID.randomUUID().toString();
        String lockKey = "lock:" + localName;
        int lockExpire = (int) (timeout / 1000);
        long end = System.currentTimeMillis() + acquireTimeout;
        while (System.currentTimeMillis() < end) {
            if (conn.setnx(lockKey, identifier) == 1) {
                conn.expire(lockKey, lockExpire);
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
}

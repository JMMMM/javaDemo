//package redis_lock;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisCommands;
//
///**
//* @author 何俊杰
//* 创建时间 20180919
//* redis分布式锁
//*/
//@Slf4j
//@Component
//public class RedisDistributedLockUtil {
//
//	private static RedisTemplate<String, Object> redisTemplate ;
//
//	@Resource(name="redisTemplate")
//	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
//		RedisDistributedLockUtil.redisTemplate = redisTemplate;
//	}
//
//    public static final String UNLOCK_LUA;
//	public static final String UNLOCK_LUA_SHA;
//
//    static {
//        StringBuilder sb = new StringBuilder();
//        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
//        sb.append("then ");
//        sb.append("    return redis.call(\"del\",KEYS[1]) ");
//        sb.append("else ");
//        sb.append("    return 0 ");
//        sb.append("end ");
//        UNLOCK_LUA = sb.toString();
//        UNLOCK_LUA_SHA = redisTemplate.scriptLoad(UNLOCK_LUA);
//    }
//
//    /**
//     * key:锁的key
//     * requestId:锁的标记，线程唯一，用于解锁时候判断是否当前获取锁的线程
//     * expire:锁有效时间，过期自动释放
//     * */
//    public static boolean setLock(String key,String requestId,long expire) {
//        try {
//            RedisCallback<String> callback = (connection) -> {
//                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
//               // String uuid = UUID.randomUUID().toString();
//                return commands.set(key, requestId, "NX", "PX", expire);
//            };
//            String result = redisTemplate.execute(callback);
//
//            return !StringUtils.isEmpty(result);
//        } catch (Exception e) {
//            log.error("set redis occured an exception", e);
//        }
//        return false;
//    }
//
//    public static String get(String key) {
//        try {
//            RedisCallback<String> callback = (connection) -> {
//                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
//                return commands.get(key);
//            };
//            String result = redisTemplate.execute(callback);
//            return result;
//        } catch (Exception e) {
//        	log.error("get redis occured an exception", e);
//        }
//        return "";
//    }
//
//    public static boolean releaseLock(String key,String requestId) {
//        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
//        try {
//            List<String> keys = new ArrayList<>();
//            keys.add(key);
//            List<String> args = new ArrayList<>();
//            args.add(requestId);
//
//            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
//            // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
//            RedisCallback<Long> callback = (connection) -> {
//                Object nativeConnection = connection.getNativeConnection();
//                // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
//                // 集群模式
//                if (nativeConnection instanceof JedisCluster) {
//                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
//                }
//
//                // 单机模式
//                else if (nativeConnection instanceof Jedis) {
//                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
//                }
//                return 0L;
//            };
//            Long result = redisTemplate.execute(callback);
//
//            return result != null && result > 0;
//        } catch (Exception e) {
//        	log.error("release lock occured an exception", e);
//        } finally {
//            // 清除掉ThreadLocal中的数据，避免内存溢出
//            //lockFlag.remove();
//        }
//        return false;
//    }
//
//}

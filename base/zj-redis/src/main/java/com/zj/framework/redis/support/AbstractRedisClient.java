package com.zj.framework.redis.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;

public abstract class AbstractRedisClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRedisClient.class);

    @Autowired
    private ShardedJedisSentinelPool pool;

    protected <T> T doInRedis(RedisCallback<T> callback) {
        ShardedJedis shardedJedis = null;
        T obj = null;
        boolean broken = false;
        try {
            shardedJedis = pool.getResource();
            if (shardedJedis == null) {
                return null;
            }
            obj = callback.excute(shardedJedis);
        } catch (Exception e) {
            LOGGER.error("get redis thread error", e);
            broken = true;
        } finally {
            if (broken) {
                pool.returnBrokenResource(shardedJedis);
            } else {
                pool.returnResource(shardedJedis);
            }
        }

        return obj;
    }
}

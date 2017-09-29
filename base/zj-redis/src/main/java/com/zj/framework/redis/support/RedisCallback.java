package com.zj.framework.redis.support;

import redis.clients.jedis.ShardedJedis;

public interface RedisCallback<T> {

    T excute(ShardedJedis connection);
}

package com.zj.framework.redis.client;

import com.alibaba.fastjson.JSON;
import com.zj.framework.redis.support.AbstractRedisClient;
import com.zj.framework.redis.support.RedisCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import java.util.*;

@Component
public class RedisClient extends AbstractRedisClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClient.class);


    public String set(final String key, final Object obj) {
        return doInRedis(new RedisCallback<String>() {
            @Override
            public String excute(ShardedJedis connection) {
                return connection.set(key, JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public Long hset(final String key, final String field, final Object obj) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.hset(key,field, JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public Boolean setnx(final String key, final Object obj) {
        return doInRedis(new RedisCallback<Boolean>() {
            @Override
            public Boolean excute(ShardedJedis connection) {
                Object result = null;
                if (obj instanceof String) {
                    result = connection.setnx(key, obj.toString());
                    return result != null && result.equals(1L) ? true : false;
                }
                result = connection.setnx(key, JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss"));
                return result != null && result.equals(1L) ? true : false;
            }
        });
    }


    public String getSet(final String key, final Object obj) {
        return doInRedis(new RedisCallback<String>() {
            @Override
            public String excute(ShardedJedis connection) {
                if (obj instanceof String) {
                    return connection.getSet(key, obj.toString());
                }
                return connection.getSet(key, JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public String setex(final String key, final Object obj, final int expireTime) {
        return doInRedis(new RedisCallback<String>() {
            @Override
            public String excute(ShardedJedis connection) {
                return connection.setex(key, expireTime, JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public String setexString(final String key, final String obj, final int expireTime) {
        return doInRedis(new RedisCallback<String>() {
            @Override
            public String excute(ShardedJedis connection) {
                return connection.setex(key, expireTime, obj);
            }
        });
    }

    public <T> T get(final String key, final Class<T> clasz) {
        return doInRedis(new RedisCallback<T>() {
            @Override
            public T excute(ShardedJedis connection) {
                return JSON.parseObject(connection.get(key), clasz);
            }
        });
    }

    public <T> Object hget(final String key, final String field, final Class<T> clasz) {
        return doInRedis(new RedisCallback<T>() {
            @Override
            public T excute(ShardedJedis connection) {
                return JSON.parseObject(connection.hget(key,field), clasz);
            }
        });
    }

    public String getString(final String key) {
       return doInRedis(new RedisCallback<String>() {
            @Override
            public String excute(ShardedJedis connection) {
                return connection.get(key);
            }
        });
    }

    public Long inc(final String key) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.incr(key);
            }
        });
    }

    public Long sadd(final String key, final Object value) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.sadd(key, JSON.toJSONStringWithDateFormat(value, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public Long srem(final String key, final Object value) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.srem(key, JSON.toJSONStringWithDateFormat(value, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public Set<String> smembers(final String key) {
        return doInRedis(new RedisCallback<Set<String>>() {
            @Override
            public Set<String> excute(ShardedJedis connection) {
                return connection.smembers(key);
            }
        });
    }

    public String spop(final String key) {
        return doInRedis(new RedisCallback<String>() {
            @Override
            public String excute(ShardedJedis connection) {
                return connection.spop(key);
            }
        });
    }

    public Long scard(final String key) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.scard(key);
            }
        });
    }

    public Boolean sismember(final String key, final Object value) {
        return doInRedis(new RedisCallback<Boolean>() {
            @Override
            public Boolean excute(ShardedJedis connection) {
                return connection.sismember(key, JSON.toJSONStringWithDateFormat(value, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public Long zadd(final String key, final double score, final Object value) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.zadd(key, score, JSON.toJSONStringWithDateFormat(value, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public Long zrem(final String key, final Object value) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.zrem(key, JSON.toJSONStringWithDateFormat(value, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public Double zincrby(final String key, final double score, final Object value) {
        return doInRedis(new RedisCallback<Double>() {
            @Override
            public Double excute(ShardedJedis connection) {
                return connection.zincrby(key, score, JSON.toJSONStringWithDateFormat(value, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public Double zscore(final String key, final Object value) {
        return doInRedis(new RedisCallback<Double>() {
            @Override
            public Double excute(ShardedJedis connection) {
                return connection.zscore(key, JSON.toJSONStringWithDateFormat(value, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public Long zcard(final String key) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.zcard(key);
            }
        });
    }

    public Set<String> zrange(final String key, final long start, final long end) {
        return doInRedis(new RedisCallback<Set<String>>() {
            @Override
            public Set<String> excute(ShardedJedis connection) {
                return connection.zrange(key, start, end);
            }
        });
    }

    public Set<String> zrevrange(final String key, final long start, final long end) {
        return doInRedis(new RedisCallback<Set<String>>() {
            @Override
            public Set<String> excute(ShardedJedis connection) {
                return connection.zrevrange(key, start, end);
            }
        });
    }

    public Set<String> zrangeByScore(final String key, final long start, final long end) {
        return doInRedis(new RedisCallback<Set<String>>() {
            @Override
            public Set<String> excute(ShardedJedis connection) {
                return connection.zrangeByScore(key, start, end);
            }
        });
    }

    public Set<String> zrevrangeByScoreWithScores(final String key, final long start, final long end) {
        return (Set<String>) doInRedis(new RedisCallback() {
            @Override
            public Object excute(ShardedJedis connection) {
                return connection.zrevrangeByScoreWithScores(key, start, end);
            }
        });
    }

    public Long lpush(final String key, final Object value) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.lpush(key, JSON.toJSONStringWithDateFormat(value, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public Long rpush(final String key, final Object value) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.rpush(key, JSON.toJSONStringWithDateFormat(value, "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }

    public List<String> lrange(final String key, final long start, final long end) {
        return doInRedis(new RedisCallback<List<String>>() {
            @Override
            public List<String> excute(ShardedJedis connection) {
                return connection.lrange(key, start, end);
            }
        });
    }

    public Long llen(final String key) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.llen(key);
            }
        });
    }

    public String ltrim(final String key, final long start, final long end) {
        return doInRedis(new RedisCallback<String>() {
            @Override
            public String excute(ShardedJedis connection) {
                return connection.ltrim(key, start, end);
            }
        });
    }

    public String lindex(final String key, final long index) {
        return doInRedis(new RedisCallback<String>() {
            @Override
            public String excute(ShardedJedis connection) {
                return connection.lindex(key, index);
            }
        });
    }

    public Long lrem(final String key, final long count, final String value) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.lrem(key, count, value);
            }
        });
    }

    public String lpop(final String key) {
        return doInRedis(new RedisCallback<String>() {
            @Override
            public String excute(ShardedJedis connection) {
                return connection.lpop(key);
            }
        });
    }

    public String rpop(final String key) {
        return doInRedis(new RedisCallback<String>() {
            @Override
            public String excute(ShardedJedis connection) {
                return connection.rpop(key);
            }
        });
    }

    public Long expire(final String key, final int seconds) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.expire(key, seconds);
            }
        });
    }

    public Long ttl(final String key) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.ttl(key);
            }
        });
    }


    public Long del(final String key) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                return connection.del(key);
            }
        });
    }

    public Long incr(final String key, final int timeout, final boolean roll) {
        return doInRedis(new RedisCallback<Long>() {
            @Override
            public Long excute(ShardedJedis connection) {
                Long b = connection.incr(key);
                if (timeout > 0) {
                    if (b == 1) {//第一次进入，设置过期时间
                        connection.expire(key, timeout);
                    }
                    if (roll && b > 1) {//时间滚动
                        connection.expire(key, timeout);
                    }
                }
                return b;
            }
        });
    }

    public Boolean exists(final String key) {
        return doInRedis(new RedisCallback<Boolean>() {
            @Override
            public Boolean excute(ShardedJedis connection) {
                return connection.exists(key);
            }
        });
    }

    public Collection<Jedis> getAllShards() {
        return (Collection<Jedis>) doInRedis(new RedisCallback() {
            @Override
            public Object excute(ShardedJedis connection) {
                return connection.getAllShards();
            }
        });
    }

    /**
     * 返回满足给定pattern的所有key
     *
     * @param pattern 正则表达式，建议不要太复杂，影响数据库性能
     * @return 返回满足给定pattern的所有key
     */
    public Set<String> keys(final String pattern) {

        return doInRedis(new RedisCallback<Set<String>>() {
            @Override
            public Set<String> excute(ShardedJedis connection) {
                Set<String> keys = new HashSet<String>();
                Collection<Jedis> jedises = getAllShards();
                for (Jedis jedis : jedises) {
                    Set<String> k = jedis.keys(pattern);
                    keys.addAll(k);
                }
                return keys;
            }
        });
    }

    public List<String> ping() {
        return doInRedis(new RedisCallback<List<String>>() {
            @Override
            public List<String> excute(ShardedJedis connection) {
                List<String> pongs = new ArrayList<>();
                Collection<Jedis> jedises = getAllShards();
                for (Jedis jedis : jedises) {
                    String pong = jedis.ping() + "\tdbSize:" + jedis.dbSize();
                    pongs.add(pong);
                }
                return pongs;
            }
        });
    }

    public List<String> info() {
        return doInRedis(new RedisCallback<List<String>>() {
            @Override
            public List<String> excute(ShardedJedis connection) {
                List<String> infos = new ArrayList<>();
                Collection<Jedis> jedises = getAllShards();
                for (Jedis jedis : jedises) {
                    String info = jedis.info();
                    infos.add(info);
                }
                return infos;
            }
        });
    }
}

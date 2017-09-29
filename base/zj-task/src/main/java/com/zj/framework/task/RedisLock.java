package com.zj.framework.task;

import com.zj.framework.redis.RedisClient;
import com.zj.framework.task.holder.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author Mr. Zhaodong<br/>
 * @version V1.0 <br/>
 * @name: RedisLock <br/>
 * @description: 分布式锁<br/>
 *
 * 参考：http://www.cnblogs.com/0201zcr/p/5942748.html
 * @date 2017/4/21 16:51 <br/>
 */
public class RedisLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

    /**
     * 锁超时时间，防止线程在入锁以后，无限的执行等待
     */
    private int expire = 60 * 1000;

    /**
     * 锁等待时间，防止线程饥饿
     */
    private int timeout = 10 * 1000;
    private String name;
    private volatile boolean locked = false;

    private RedisClient redisClientTemplate;

    public RedisLock(String name) {
        this.name = name + "_lock";
        this.redisClientTemplate = SpringContextHolder.getBean(RedisClient.class);
    }

    public RedisLock(String name, int expire) {
        this(name);
        this.expire = expire;
    }

    public RedisLock(String name, int timeout, int expire) {
        this(name);
        this.timeout = timeout;
        this.expire = expire;
    }


    public String getName() {
        return name;
    }

    /**
     * 获得 lock.
     * 实现思路: 主要是使用了redis 的setnx命令,缓存了锁.
     * reids缓存的key是锁的key,所有的共享, value是锁的到期时间(注意:这里把过期时间放在value了,没有时间上设置其超时时间)
     *
     * 执行过程:
     * 1.通过setnx尝试设置某个key的值,成功(当前没有这个锁)则返回,成功获得锁
     * 2.锁已经存在则获取锁的到期时间,和当前时间比较,超时的话,则设置新的值
     */
    public synchronized boolean lock() throws InterruptedException {
        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + expire + 1;
            //锁到期时间
            String expiresStr = String.valueOf(expires);
            if (redisClientTemplate.setnx(name, expiresStr)) {
                locked = true;
                return true;
            }

            String currentLockValue = (String) redisClientTemplate.get(name, String.class);
            //判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
            if (currentLockValue != null && Long.parseLong(currentLockValue) < System.currentTimeMillis()) {
                String oldLockValue = redisClientTemplate.getSet(name, expiresStr);
                //获取上一个锁到期时间，并设置现在的锁到期时间，只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
                if (oldLockValue != null && oldLockValue.equals(currentLockValue)) {
                    //防止误删（覆盖，因为key是相同的）了他人的锁——这里达不到效果，这里值会被覆盖，但是因为什么相差了很少的时间，所以可以接受
                    //[分布式的情况下]:如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
                    locked = true;
                    return true;
                }
            }
            int acquiryResolutionMillis = new Random().nextInt(100)+10;
            timeout -= acquiryResolutionMillis;
            /**
             * 延迟100 毫秒,这里使用随机时间可能会好一点,可以防止饥饿进程的出现,即当同时到达多个进程,
             * 只会有一个进程获得锁,其他的都用同样的频率进行尝试,后面有来了一些进程,也以同样的频率申请锁,
             * 这将可能导致前面来的锁得不到满足.使用随机的等待时间可以一定程度上保证公平性
             */
            Thread.sleep(acquiryResolutionMillis);
        }
        return false;
    }


    /**
     * 释放分布式锁
     */
    public synchronized void unlock() {
        if (locked) {
            redisClientTemplate.del(name);
            locked = false;
        }
    }

}

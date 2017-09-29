package com.zj.framework.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mr. Zhaodong<br/>
 * @version V1.0 <br/>
 * @name: LockTask <br/>
 * @description: 分布式锁<br/>
 * @date 2016/5/17 0:13 <br/>
 */
public class LockTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(LockTask.class);


    protected void executeAfterLocked(LockType type, ITask iTask) {
        boolean locked = false;

        RedisLock redisLock = new RedisLock(type.getName(), type.getExpire());
        try {
            if (redisLock.lock()) {
                locked = true;
            }
            if (!locked) {
                return;
            }
            iTask.execute();
            LOGGER.info("executeAfterLocked: Lock " + redisLock.getName());
        } catch (Exception e) {
            LOGGER.error("executeAfterLocked[status:" + redisLock.getName() + "] Error: ", e);
        } finally {
            if (locked) {
                redisLock.unlock();
                LOGGER.info("executeAfterLocked: Unlock " + redisLock.getName());
            }
        }
    }
}

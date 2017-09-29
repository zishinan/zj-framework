package com.zj.framework.service.user.impl.task;

import com.zj.framework.task.LockType;

/**
 * @author Mr. Zhaodong<br/>
 * @version V1.0 <br/>
 * @name: LockType <br/>
 * @description: 锁类型<br/>
 * @date 2016/12/27 15:02 <br/>
 */
public enum UserLockType implements LockType {
    /**
     * vip等级和成长值变更
     */
    VIP_BIRTHDAY_TASK_LOCK(60 * 60 * 1000)
    ;

    private int expire;

    UserLockType(int expire) {
        this.expire = expire;
    }
    @Override
    public int getExpire() {
        return expire;
    }

    @Override
    public String getName() {
        return name();
    }
}

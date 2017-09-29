package com.zj.framework.task;

/**
 * @author Mr. Zhaodong<br/>
 * @version V1.0 <br/>
 * @name: LockType <br/>
 * @description: 抽象锁类型<br/>
 * @date 2016/12/27 15:02 <br/>
 */
public interface LockType {

    String getName();

    int getExpire();
}

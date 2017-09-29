package com.zj.framework.service.user.impl.task;

import com.zj.framework.service.user.UserService;
import com.zj.framework.task.ITask;
import com.zj.framework.task.LockTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  vip等级变更后台任务
 *  每天执行，选出vip最后变更时间小于now-180天的计算
 *  先扣除成长值再变更等级，变更了都要记录日志
 */
@Service
public class VipBirthdayTask extends LockTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(VipBirthdayTask.class);


    @Resource
    private UserService userService;

    // 每个周一上午4点
    @Scheduled(cron = "0 0 4 ? * MON")
    public void task() {
        executeAfterLocked(UserLockType.VIP_BIRTHDAY_TASK_LOCK, new ITask() {
            @Override
            public void execute() {
                try {
                    long start = System.currentTimeMillis();

                    LOGGER.debug("############# task take time : {}s ", (double) (System.currentTimeMillis() - start) / 1000);
                }catch (Exception e){
                    LOGGER.error("生日统计服务错误："+e.getMessage());
                }
            }
        });
    }


}

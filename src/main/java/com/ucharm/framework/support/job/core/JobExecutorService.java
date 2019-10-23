package com.ucharm.framework.support.job.core;

import java.time.temporal.ChronoUnit;

/**
 * @Package com.ucharm.framework.support.job.core
 * @author: xuchao（xxc727xxc@foxmail.com）
 * @date: 2019-10-22 12:43
 */
public interface JobExecutorService {

    /**
     * 提交延时任务
     *
     * @param delayJobHandlerClass 延时任务执行器class
     * @param paramObject          入参对象
     * @param delay                延时
     * @param unit                 延时单位
     * @param <T>
     */
    <T extends JobParam> void submitWithFixedDelay(Class<? extends DelayJobHandler<T>> delayJobHandlerClass, T paramObject, long delay, ChronoUnit unit);
}

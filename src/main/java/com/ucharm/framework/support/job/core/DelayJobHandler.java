package com.ucharm.framework.support.job.core;

/**
 * @Package com.ucharm.framework.support.job.core
 * @author: xuchao（xxc727xxc@foxmail.com）
 * @date: 2019-10-22 19:45
 */
public interface DelayJobHandler<T extends JobParam> {

    /**
     * 执行延时任务,抛出异常则终止
     *
     * @param paramObject
     */
     void doDelayJob(T paramObject);
}

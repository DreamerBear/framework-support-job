package com.ucharm.framework.support.job.core;

/**
 * @Package com.ucharm.framework.support.job.core
 * @author: xuchao（xxc727xxc@foxmail.com）
 * @date: 2019-10-22 19:43
 */
public interface BaseJobHandler {
    /**
     * 执行任务,抛出异常则重试
     *
     * @param param
     */
    void doJob(String param);
}

package com.ucharm.framework.support.job.core.xxl;

import com.alibaba.fastjson.JSON;
import com.ucharm.framework.support.job.core.DelayJobHandler;
import com.ucharm.framework.support.job.core.JobParam;

import java.lang.reflect.ParameterizedType;

/**
 * xxl延时任务基类
 * <p>
 * 入参反序列化
 * 执行延时任务
 * 执行完成后删除任务
 *
 * @Package com.ucharm.framework.support.job.core
 * @author: xuchao（xxc727xxc@foxmail.com）
 * @date: 2019-10-22 17:28
 */
public abstract class XxlDelayJobHandler<T extends JobParam> extends XxlBaseJobHandler implements DelayJobHandler<T> {

    @Override
    public void doJob(String param) {
        //入参反序列化
        Class<T> paramClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T paramObject = JSON.parseObject(param, paramClass);

        //执行延时任务
        doDelayJob(paramObject);

        //执行完成后删除任务
        XxlJobAdminClient.killDelayJob(paramObject);
    }

}

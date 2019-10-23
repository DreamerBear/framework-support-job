package com.ucharm.framework.support.job.core;

import com.alibaba.fastjson.JSON;
import com.ucharm.framework.support.job.core.xxl.XxlDelayJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

/**
 * @Package com.ucharm.framework.support.job.core
 * @author: xuchao（xxc727xxc@foxmail.com）
 * @date: 2019-10-23 09:58
 */
@JobHandler("testDelayJob")
public class TestDelayJob extends XxlDelayJobHandler<TestJobParam> {
    @Override
    public void doDelayJob(TestJobParam paramObject) {
        System.out.println(String.format("hello world, paramObject: %s",
                JSON.toJSONString(paramObject, true))
        );
    }
}

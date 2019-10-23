package com.ucharm.framework.support.job.core;


import com.ucharm.framework.support.job.core.xxl.XxlJobAdminClient;
import com.ucharm.framework.support.job.core.xxl.XxlJobExecutorService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.temporal.ChronoUnit;

/**
 * @Package com.ucharm.framework.support.job.core
 * @author: xuchao（xxc727xxc@foxmail.com）
 * @date: 2019-10-22 14:15
 */
public class XxlJobExecutorServiceTest {


    @Test
    public void executeWithFixedDelay() {
        //mock
        XxlJobExecutorService xxlJobExecutorService = new XxlJobExecutorService("xuchao", "xiongxc@ucharm.com",1);
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        xxlJobExecutorService.setApplicationContext(context);
        XxlJobAdminClient.init("http://192.168.110.58:8100/xxl-job-admin");

        //doTest
        TestJobParam jobParam = new TestJobParam();
        jobParam.setFoo("how are you");
        jobParam.setBar(-1);
        xxlJobExecutorService.submitWithFixedDelay(TestDelayJob.class, jobParam, 10, ChronoUnit.MINUTES);
    }
}

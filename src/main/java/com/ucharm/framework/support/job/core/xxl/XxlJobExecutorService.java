package com.ucharm.framework.support.job.core.xxl;

import com.alibaba.fastjson.JSON;
import com.ucharm.framework.support.job.core.DelayJobHandler;
import com.ucharm.framework.support.job.core.JobExecutorService;
import com.ucharm.framework.support.job.core.JobParam;
import com.ucharm.framework.support.job.util.CronUtil;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * @Package com.ucharm.framework.support.job.core
 * @author: xuchao（xxc727xxc@foxmail.com）
 * @date: 2019-10-22 12:56
 */
public class XxlJobExecutorService implements JobExecutorService, ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 负责人
     */
    private String author;

    /**
     * 报警邮件
     */
    private String alarmEmail;

    /**
     * 执行器分组id
     */
    private int jobGroup;

    public XxlJobExecutorService(String author,String alarmEmail,int jobGroup){
        this.author = author;
        this.alarmEmail = alarmEmail;
        this.jobGroup = jobGroup;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T extends JobParam> void submitWithFixedDelay(Class<? extends DelayJobHandler<T>> delayJobHandlerClass, T paramObject, long delay, ChronoUnit unit) {

        //任务匹配
        DelayJobHandler<T> delayJobHandler = applicationContext.getBean(delayJobHandlerClass);

        if (Objects.isNull(delayJobHandler)) {
            throw new IllegalArgumentException(String.format("Wrong DelayJobHandlerClass, %s", delayJobHandlerClass));
        }

        //获取执行器名称
        JobHandler annotation = delayJobHandlerClass.getAnnotation(JobHandler.class);
        if (Objects.isNull(annotation)) {
            throw new IllegalArgumentException(String.format("delayJobHandlerClass is not annotated with @JobHandler, %s", delayJobHandlerClass));
        }
        String executorHandler = annotation.value();

        //入参序列化
        String executorParam = JSON.toJSONString(paramObject);

        //cron表达式生成
        String jobCron = CronUtil.generateCronByFixedDelay(delay, unit);

        //提交任务
        XxlJobInfo xxlJobInfo = new XxlJobInfo();
        xxlJobInfo.setJobGroup(jobGroup);
        xxlJobInfo.setJobCron(jobCron);
        xxlJobInfo.setExecutorHandler(executorHandler);
        xxlJobInfo.setExecutorParam(executorParam);
        xxlJobInfo.setJobDesc("延时任务:"+executorHandler);
        xxlJobInfo.setAuthor(author);
        xxlJobInfo.setAlarmEmail(alarmEmail);
        XxlJobAdminClient.submitDelayJob(xxlJobInfo);
    }

}

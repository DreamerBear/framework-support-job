package com.ucharm.framework.support.job.autoconfigure;

import com.ucharm.framework.support.job.core.JobExecutorService;
import com.ucharm.framework.support.job.core.xxl.XxlJobAdminClient;
import com.ucharm.framework.support.job.core.xxl.XxlJobExecutorService;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
@Configuration
@ConditionalOnProperty(prefix = "job.xxl", name = "enabled", havingValue = "true")
public class XxlJobConfiguration {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfiguration.class);

    @Value("${job.xxl.admin.addresses}")
    private String adminAddresses;

    @Value("${job.xxl.group.name}")
    private String groupName;

    @Value("${job.xxl.executor.ip:}")
    private String ip;

    @Value("${job.xxl.executor.port}")
    private int port;

    @Value("${job.xxl.accessToken:}")
    private String accessToken;

    @Value("${job.xxl.executor.logpath}")
    private String logPath;

    @Value("${job.xxl.executor.logretentiondays}")
    private int logRetentionDays;

    /**
     * 负责人
     */
    @Value("${job.xxl.executor.author}")
    private String author;

    /**
     * 报警邮件
     */
    @Value("${job.xxl.executor.alarmEmail}")
    private String alarmEmail;

    /**
     * 执行器分组id
     */
    private int jobGroup;

    @Bean(initMethod = "start", destroyMethod = "destroy", value = "xxlJobSpringExecutor")
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info("========= xxlJobSpringExecutor init =========");
        XxlJobAdminClient.init(adminAddresses);
        jobGroup = XxlJobAdminClient.saveGroup(groupName);
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(groupName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobSpringExecutor;
    }

    @Bean
    @DependsOn("xxlJobSpringExecutor")
    public JobExecutorService jobExecutorService() {
        logger.info("========= jobExecutorService init =========");
        return new XxlJobExecutorService(author, alarmEmail, jobGroup);
    }
}
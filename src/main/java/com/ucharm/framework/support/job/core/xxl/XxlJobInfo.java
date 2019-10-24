package com.ucharm.framework.support.job.core.xxl;

import lombok.Data;

/**
 * xxl-job info
 *
 * @author xuxueli  2016-1-12 18:25:49
 */
@Data
public class XxlJobInfo {

    /*=====必填参数=====*/
    /**
     * 执行器分组id
     */
    private int jobGroup;

    /**
     * 延时
     */
    private long delay;

    /**
     * 延时单位
     */
    private String unit;

    /**
     * 执行器，任务Handler名称
     */
    private String executorHandler;

    /**
     * 执行器，任务参数
     */
    private String executorParam;

    /**
     * 任务描述
     */
    private String jobDesc;

    /**
     * 负责人
     */
    private String author;

    /**
     * 报警邮件
     */
    private String alarmEmail;
    /*=====必填参数=====*/

    /*=====带默认值参数=====*/
    /**
     * 执行器路由策略
     */
    private String executorRouteStrategy = "ROUND";

    /**
     * 阻塞处理策略
     */
    private String executorBlockStrategy = "SERIAL_EXECUTION";

    /**
     * 任务执行超时时间，单位秒
     */
    private int executorTimeout = 0;

    /**
     * 失败重试次数
     */
    private int executorFailRetryCount = 0;

    /**
     * GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
     */
    private String glueType = "BEAN";

    /**
     * 调度状态：0-停止，1-运行
     */
    private int triggerStatus = 1;
    /*=====带默认值参数=====*/
}

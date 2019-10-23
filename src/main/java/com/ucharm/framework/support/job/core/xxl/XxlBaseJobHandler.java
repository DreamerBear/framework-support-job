package com.ucharm.framework.support.job.core.xxl;

import com.ucharm.framework.support.job.core.BaseJobHandler;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * xxl任务基类
 *
 * @author xuchao
 */
public abstract class XxlBaseJobHandler extends IJobHandler implements BaseJobHandler {

    @Override
    public ReturnT<String> execute(String param) {
        try {
            doJob(param);
            return SUCCESS;
        } catch (Exception e) {
            logError(e);
            return FAIL;
        }
    }

    /**
     * 打印info日志
     *
     * @param appendLogPattern
     * @param appendLogArguments
     */
    public void logInfo(String appendLogPattern, Object... appendLogArguments) {
        XxlJobLogger.log(appendLogPattern, appendLogArguments);
    }

    /**
     * 打印错误日志
     *
     * @param e
     */
    public static void logError(Throwable e) {
        XxlJobLogger.log(e);
    }
}
package com.ucharm.framework.support.job.annotation;

import com.ucharm.framework.support.job.autoconfigure.JobConfigurationSelector;
import com.ucharm.framework.support.job.constant.JobProvider;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Package com.ucharm.framework.support.job
 * @author: xuchao（xxc727xxc@foxmail.com）
 * @date: 2019-10-22 11:38
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({JobConfigurationSelector.class})
public @interface EnableJob {

    /**
     * 定时任务提供方
     *
     * @return
     */
    JobProvider provider() default JobProvider.XXL;
}

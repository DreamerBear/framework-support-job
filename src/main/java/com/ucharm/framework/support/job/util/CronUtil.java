package com.ucharm.framework.support.job.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @Package com.ucharm.framework.support.job.util
 * @author: xuchao（xxc727xxc@foxmail.com）
 * @date: 2019-10-22 17:16
 */
public class CronUtil {
    private CronUtil() {

    }

    /**
     * 根据指定时延生成cron表达式
     *
     * @param delay
     * @param unit
     * @return
     */
    public static String generateCronByFixedDelay(long delay, ChronoUnit unit) {
        LocalDateTime nextValidTime = LocalDateTime.now().plus(delay, unit);
        return DateTimeFormatter.ofPattern("ss mm HH dd MM ? yyyy").format(nextValidTime);
    }

    public static void main(String[] args) {
        System.out.println(generateCronByFixedDelay(1, ChronoUnit.HOURS));
    }
}

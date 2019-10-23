package com.ucharm.framework.support.job.core.xxl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.ucharm.framework.support.job.core.JobParam;

import java.util.Objects;

/**
 * @Package com.ucharm.framework.support.job.core
 * @author: xuchao（xxc727xxc@foxmail.com）
 * @date: 2019-10-22 19:34
 */
public class XxlJobAdminClient {

    /**
     * e.g. http://127.0.0.1:8100/xxl-job-admin
     */
    private static String adminUrl;

    /**
     * e.g. http://127.0.0.1:8100/xxl-job-admin/delayjob/submit
     */
    private static String submitDelayJobUrl;

    /**
     * e.g. http://127.0.0.1:8100/xxl-job-admin/delayjob/kill
     */
    private static String killDelayJobUrl;

    /**
     * e.g. http://127.0.0.1:8100/xxl-job-admin/basejob/group/save
     */
    private static String saveGroupUrl;

    public static void init(String adminAddresses) {
        adminUrl = adminAddresses;
        submitDelayJobUrl = URLUtil.normalize(adminUrl + "/delayjob/submit");
        killDelayJobUrl = URLUtil.normalize(adminUrl + "/delayjob/kill");
        saveGroupUrl = URLUtil.normalize(adminUrl + "/basejob/group/save");
    }

    /**
     * 提交延时任务,添加失败则抛出异常
     *
     * @param xxlJobInfo
     */
    public static void submitDelayJob(XxlJobInfo xxlJobInfo) {
        Objects.requireNonNull(submitDelayJobUrl);
        HttpRequest request = HttpUtil.createPost(submitDelayJobUrl);
        request.form(BeanUtil.beanToMap(xxlJobInfo));
        HttpResponse response = request.execute();
        if (Objects.isNull(response) || !Objects.equals(HttpStatus.HTTP_OK, response.getStatus())) {
            throw new RuntimeException(
                    String.format("submitDelayJob error, request: %s, response: %s",
                            JSON.toJSONString(request),
                            JSON.toJSONString(response)
                    )
            );
        }
    }

    /**
     * 杀死延时任务,添加失败则抛出异常
     *
     * @param jobParam
     */
    public static void killDelayJob(JobParam jobParam) {
        Objects.requireNonNull(killDelayJobUrl);
        HttpRequest request = HttpUtil.createPost(killDelayJobUrl);
        request.form("jobId", jobParam.getJobId());
        HttpResponse response = request.execute();
        if (Objects.isNull(response) || !Objects.equals(HttpStatus.HTTP_OK, response.getStatus())) {
            throw new RuntimeException(
                    String.format("killDelayJob error, request: %s, response: %s",
                            JSON.toJSONString(request),
                            JSON.toJSONString(response)
                    )
            );
        }
    }

    /**
     * 保存执行器分组
     *
     * @param groupName
     * @return 执行器分组id
     */
    public static int saveGroup(String groupName) {
        Objects.requireNonNull(saveGroupUrl);
        HttpRequest request = HttpUtil.createPost(saveGroupUrl);
        request.form("appName", groupName);
        HttpResponse response = request.execute();
        if (Objects.isNull(response) || !Objects.equals(HttpStatus.HTTP_OK, response.getStatus())) {
            throw new RuntimeException(
                    String.format("killDelayJob error, request: %s, response: %s",
                            JSON.toJSONString(request),
                            JSON.toJSONString(response)
                    )
            );
        }
        return Integer.parseInt(response.body());
    }
}

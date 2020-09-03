package com.c332030.task.test.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import org.quartz.JobDetail;
import org.quartz.Trigger;

import lombok.extern.slf4j.Slf4j;

import com.c332030.task.test.job.TestQuartzJob;

/**
 * <p>
 * Description: JobTest
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
@Configuration
public class TestQuartzJobConfigure {

    @Bean
    public MethodInvokingJobDetailFactoryBean jobDetailFactoryBean(TestQuartzJob testQuartzJob) {

        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();

        // 是否并发执行
        jobDetail.setConcurrent(false);

        // 设置任务的名字
        jobDetail.setName("testQuartzJob");

        // 设置任务的分组，在多任务的时候使用
        jobDetail.setGroup("testQuartzJobGroup");

        // 需要执行的对象
        jobDetail.setTargetObject(testQuartzJob);

        /*
         * TODO  非常重要
         * 执行QuartzTask类中的需要执行方法
         */
        jobDetail.setTargetMethod("execute");
        return jobDetail;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetail jobDetail) {

        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();

        trigger.setJobDetail(jobDetail);

        //cron表达式，每1分钟执行一次
        trigger.setCronExpression("*/5 * * * * ?");
        trigger.setName("testQuartzTrigger");

        return trigger;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger trigger) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();

        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        factoryBean.setOverwriteExistingJobs(true);

        // 延时启动，应用启动1秒后
        factoryBean.setStartupDelay(1);

        // 注册触发器
        factoryBean.setTriggers(trigger);

        return factoryBean;
    }
}

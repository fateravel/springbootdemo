package com.example.config;

import com.example.job.FirstJob;
import com.example.job.SecondJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * PLAN A
 * @author pengsong
 */
//@Configuration
public class QuartzConfig {

    @Bean
    public MethodInvokingJobDetailFactoryBean firstJobDetail(FirstJob firstJob) {
        return getJobDetail(firstJob);
    }

    @Bean
    public SimpleTriggerFactoryBean firstTrigger(@Qualifier("firstJobDetail") JobDetail firstJob) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(firstJob);
        trigger.setStartDelay(0);
        trigger.setRepeatInterval(5000);
        return trigger;
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean secondJobDetail(SecondJob secondJob) {
        return getJobDetail(secondJob);
    }

    @Bean
    public CronTriggerFactoryBean secondTrigger(@Qualifier("secondJobDetail") JobDetail secondJobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(secondJobDetail);
        trigger.setStartDelay(0);
        trigger.setCronExpression("0/10 * * * * ? *");
        return trigger;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger firstTrigger, Trigger secondTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setStartupDelay(1);
        bean.setTriggers(firstTrigger, secondTrigger);
        return bean;
    }

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        return ((triggerFiredBundle, scheduler) -> applicationContext.getBean(triggerFiredBundle.getJobDetail().getJobClass()));
    }

    private MethodInvokingJobDetailFactoryBean getJobDetail(Object job) {
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        jobDetailFactoryBean.setConcurrent(false);
        jobDetailFactoryBean.setTargetObject(job);
        jobDetailFactoryBean.setTargetMethod("task");
        return jobDetailFactoryBean;
    }
}

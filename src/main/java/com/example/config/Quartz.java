package com.example.config;

import com.example.component.BaseJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * PLAN B
 * @author pengsong
 */
@Configuration
public class Quartz {

    private final List<BaseJob> jobs;

    public Quartz(ApplicationContext applicationContext) {
        this.jobs = new ArrayList<>();
        Map<String, BaseJob> beanMap = applicationContext.getBeansOfType(BaseJob.class);
        beanMap.forEach((k, v) -> jobs.add(v));
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setStartupDelay(10);
        //factory.setQuartzProperties(setting.getProperties());
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        factory.setAutoStartup(true);
        factory.setJobFactory(jobFactory);
        Trigger[] triggers = new Trigger[jobs.size()];
        JobDetail[] jobDetails = new JobDetail[jobs.size()];
        for (int i = 0; i < jobs.size(); i++) {
            triggers[i] = jobs.get(i).getTrigger();
            jobDetails[i] = jobs.get(i).getJobDetail();
        }
        factory.setTriggers(triggers);
        factory.setJobDetails(jobDetails);
        return factory;
    }

    @Bean
    public JobFactory jobFactory(ApplicationContext con) {
        return (bundle, scheduler) -> con.getBean(bundle.getJobDetail().getJobClass());
    }
}

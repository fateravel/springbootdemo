package com.example.job;

import com.example.component.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author pengsong
 */
@Service
@DisallowConcurrentExecution
public class NewJob2 extends BaseJob {

    private String cronExpression = "1/10 * * * * ? ";

    @Override
    public void executeJob(JobExecutionContext context) {
        System.out.println("new Job 2 executed, time:" + new Date());
    }

    @Override
    public String getCronExpression() {
        return cronExpression;
    }
}

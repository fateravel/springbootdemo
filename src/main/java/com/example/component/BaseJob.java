package com.example.component;

import org.quartz.*;

/**
 * @author pengsong
 */
public abstract class BaseJob implements Job{

    public abstract void executeJob(JobExecutionContext context);

    public abstract String getCronExpression();

    protected String getJonGroupName() {
        return "DEMO_JOB_GROUP";
    }

    protected String getTriggerGroupName() {
        return "DEMO_TRIGGER_GROUP";
    }

    public Trigger getTrigger() {
        return getTrigger(getCronExpression());
    }

    public Trigger getTrigger(String cron) {
        if (cron == null || cron.trim().length() == 0) {
            throw new RuntimeException("CronExpression can't null");
        }
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        return TriggerBuilder.newTrigger().withIdentity(cron, getTriggerGroupName())
                .withSchedule(scheduleBuilder).forJob(getJobDetail()).build();
    }

    public JobDetail getJobDetail() {
        String jobName = this.getClass().toString();
        return JobBuilder.newJob(this.getClass()).withIdentity(jobName, getJonGroupName())
                .storeDurably(true).requestRecovery(true).build();
    }


    @Override
    public void execute(JobExecutionContext context) {
        //System.out.println("Start job");
        try {
            executeJob(context);
            System.out.println("--------------------------");
        } catch (Exception e) {

        }
        //System.out.println("End job");
    }
}

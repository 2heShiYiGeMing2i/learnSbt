package jAvA.HellowQuartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhaolei on 2018/7/21
 */
public class HelloScheduler {
    private static final String cron1 = "0 15 10 ? * * 2017";
    private static final String cron2 = "0/5 * 14,18 * * ?";
    private static final String cron3 = "0 15 10 ? * 2-6";
    private static final String cron4 = "0 15 10 L * *";

    public static void main(String[] args) throws SchedulerException {
        // jobDetail 细节绑定 exce
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.err.println("main current time is" + sf.format(calendar.getTime()));

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job", "job group").build();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("cron trigger", "group")
                .withSchedule(CronScheduleBuilder.cronSchedule(cron1))
                .build();

        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, cronTrigger);


    }
}



// cron 表达式 cron table
// s m h d M w y
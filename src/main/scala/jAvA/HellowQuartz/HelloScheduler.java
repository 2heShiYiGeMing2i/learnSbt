package jAvA.HellowQuartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhaolei on 2018/7/21
 */
public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        // jobDetail 细节绑定 exce
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myjob", "group1")
                .build();
        Date startDate = new Date();
        Date endDate = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.err.println("current time is" + sf.format(startDate.getTime()));
        startDate.setTime(startDate.getTime() + 4000L);
        endDate.setTime(endDate.getTime() + 6000L);

        // 执行 规则
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("mytrigger", "triggerGroup")
                .startAt(startDate)
                .endAt(endDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(3))
                .build();


        java.util.Calendar calendar = Calendar.getInstance();
        System.err.println("mian current time is" + sf.format(calendar.getTime()));

        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);


    }
}

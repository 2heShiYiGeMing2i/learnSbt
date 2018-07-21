package jAvA.HellowQuartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhaolei on 2018/7/21
 */
public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        // jobDetail 细节绑定 exce
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myjob", "group1")
                .build();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.SECOND, 3);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.SECOND, 6);

        // 执行 规则
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("mytrigger", "triggerGroup")
                .startAt(startCalendar.getTime())
                .endAt(endCalendar.getTime())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();


        java.util.Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.err.println("mian current time is" + sf.format(calendar.getTime()));

        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);


    }
}

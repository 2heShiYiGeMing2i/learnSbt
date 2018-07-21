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
                .usingJobData("key1","value1")
                .usingJobData("key2",1.2F)
                .build();
//        System.err.println("jobDetail names " + jobDetail.getKey().getName());
//        System.err.println("jobDetail group " + jobDetail.getKey().getGroup());
//        System.err.println("jobDetail class " + jobDetail.getJobClass().getName());

        // 执行 规则
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("mytrigger", "triggerGroup")
                .usingJobData("key1","trigger value1")
                .usingJobData("triggerKey2",4.5D)
                .startNow()
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

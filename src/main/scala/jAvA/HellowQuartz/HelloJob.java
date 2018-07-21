package jAvA.HellowQuartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhaolei on 2018/7/21
 */
public class HelloJob implements Job {

    private String key1;
    private float key2;
    private double triggerKey2;

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public float getKey2() {
        return key2;
    }

    public void setKey2(float key2) {
        this.key2 = key2;
    }

    public double getTriggerKey2() {
        return triggerKey2;
    }

    public void setTriggerKey2(double triggerKey2) {
        this.triggerKey2 = triggerKey2;
    }

    // 业务逻辑 job detail

    @Override
    public void execute(JobExecutionContext context) {
        System.err.println("*****************************************************");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.err.println("current time is" + sf.format(calendar.getTime()));





//
//        JobKey key = context.getJobDetail().getKey();
//        System.err.println("detail key " + key.getName() + "  " + key.getGroup());
//
//        Trigger trigger = context.getTrigger();
//        TriggerKey triggerKey = trigger.getKey();
//        System.err.println("trigger key " + triggerKey.getName() + "  " + triggerKey.getGroup());
//
//        JobDetail jobDetail = context.getJobDetail();
//        JobDataMap jobDataMap = jobDetail.getJobDataMap();
//        JobDataMap triggerJobDataMap = trigger.getJobDataMap();
//        String jobDataMapKey = jobDataMap.getString("key1");
//        Float aFloat = jobDataMap.getFloat("key2");
//        String triggerJobDataMapKey = triggerJobDataMap.getString("key1");
//        Double aDouble = triggerJobDataMap.getDouble("triggerKey2");
//
//        System.err.println("job message: " + jobDataMapKey);
//        System.err.println("job message: " + triggerJobDataMapKey);
//        System.err.println("job message: " + aFloat);
//        System.err.println("job message: " + aDouble);
//
//
//        String key1 = context.getMergedJobDataMap().getString("key1");
//        System.err.println("key  "  +  key1);
//
//
//        System.err.println("new job message: " + getKey1());
//        System.err.println("new job message: " + getKey2());
//        System.err.println("new job message: " + getTriggerKey2());

    }
}

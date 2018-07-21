package jAvA.HellowQuartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Trigger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhaolei on 2018/7/21
 */
public class HelloJob implements Job {


    // 业务逻辑 job detail

    @Override
    public void execute(JobExecutionContext context) {
        System.err.println("*****************************************************");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.err.println("current time is" + sf.format(calendar.getTime()));

    }
}

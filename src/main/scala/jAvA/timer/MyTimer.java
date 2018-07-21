package jAvA.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

/**
 * Created by zhaolei on 2018/7/20
 */
public class MyTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimeTaskTest timeTaskTest = new TimeTaskTest("no 1");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        System.err.println("current time is" + sf.format(calendar.getTime()));
//        calendar.add(Calendar.SECOND,5);
        System.err.println("current time is" + sf.format(calendar.getTime()));

//        timer.schedule(timeTaskTest,calendar.getTime());

//        timer.schedule(timeTaskTest,2000L);

        timer.schedule(timeTaskTest,3000L);

        System.err.println(sf.format(timeTaskTest.scheduledExecutionTime()));
        timer.cancel();


    }
}

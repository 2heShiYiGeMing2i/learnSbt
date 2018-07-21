package jAvA.timer;

import javafx.concurrent.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

/**
 * Created by zhaolei on 2018/7/20
 */
public class Exector {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.err.println("current time is " + sf.format(calendar.getTime()));
        DanceRobot danceRobot = new DanceRobot();
        WaterRobot waterRobot = new WaterRobot(timer);
        timer.schedule(danceRobot, calendar.getTime(), 2000);
        timer.schedule(waterRobot, calendar.getTime(), 1000);

    }
}

package jAvA.timer;

import java.text.SimpleDateFormat;
import java.util.TimerTask;

/**
 * Created by zhaolei on 2018/7/20
 */
public class DanceRobot extends TimerTask {
    @Override
    public void run() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy mm dd hh mm ss");
        System.err.println("DanceRobot time is " + sf.format(scheduledExecutionTime()));
        System.err.println("dancing");
    }
}

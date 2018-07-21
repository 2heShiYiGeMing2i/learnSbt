package jAvA.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhaolei on 2018/7/20
 */
public class WaterRobot extends TimerTask {
    private int bucket = 0;
    private Timer timer;

    public WaterRobot(Timer inputTimer) {
        timer = inputTimer;
    }

    @Override
    public void run() {
        System.err.println("add 1L water into bucket");
        bucket++;
        if (bucket >= 5) {
            System.err.println("bucket is full");
            cancel();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {

            }
            timer.cancel();
        }
    }
}

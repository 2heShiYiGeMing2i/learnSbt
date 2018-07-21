package jAvA.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

/**
 * Created by zhaolei on 2018/7/20
 */
public class TimeTaskTest extends TimerTask {
    private String name;
    private int count = 0;

    public TimeTaskTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        // 用格式 yyyy-mm-dd 00:00:00
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.err.println("current time is" + sf.format(calendar.getTime()));

        // 打印当前name的内容
        System.err.println("current name is " + name);
        count ++;
        if (count >=20){
            System.err.println("task cancel");
            cancel();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

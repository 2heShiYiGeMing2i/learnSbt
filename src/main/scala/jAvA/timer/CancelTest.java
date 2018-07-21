package jAvA.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created by zhaolei on 2018/7/20
 */
public class CancelTest {
    public static void main(String[] args) throws InterruptedException {

        Timer timer = new Timer();


        Date startDate = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy:mm:dd hh:mm:ss");
        System.err.println("start time is " + sf.format(startDate));



        System.err.println("end");

    }


}

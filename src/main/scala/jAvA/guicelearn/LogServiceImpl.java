package jAvA.guicelearn;

/**
 * Created by zhaolei on 2018/2/23
 */
public class LogServiceImpl implements LogService {
    @Override
    public void log(String msg) {
        System.err.println(" =============log: " + msg);
    }
}

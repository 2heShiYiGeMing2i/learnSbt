package jAvA.guicelearn;

/**
 * Created by zhaolei on 2018/2/23
 */
public class UserServiceImpl implements UserService {
    @Override
    public void process() {
        System.err.println("我需要做一些业务逻辑");
    }
}

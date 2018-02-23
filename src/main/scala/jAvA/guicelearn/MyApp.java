package jAvA.guicelearn;


import com.google.inject.Inject;

/**
 * Created by zhaolei on 2018/2/23
 */
public class MyApp implements Application {

    private UserService userService;
    private LogService logService;


    @Inject
    public MyApp(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @Override
    public void work() {
        userService.process();
        logService.log("程序正常");
    }
}

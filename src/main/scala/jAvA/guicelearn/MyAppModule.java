package jAvA.guicelearn;

import com.google.inject.AbstractModule;

/**
 * Created by zhaolei on 2018/2/23
 */
public class MyAppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LogService.class).to(LogServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(Application.class).to(MyApp.class);
    }
}

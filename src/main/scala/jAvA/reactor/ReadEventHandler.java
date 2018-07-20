package jAvA.reactor;

/**
 * Created by zhaolei on 2018/7/19
 */
public class ReadEventHandler extends EventHandler {
    @Override
    public void handle(Event event) {
        if (event.getType() != EventType.READ) return;


    }
}

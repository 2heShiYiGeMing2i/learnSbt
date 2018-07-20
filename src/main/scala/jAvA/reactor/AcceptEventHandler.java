package jAvA.reactor;

/**
 * Created by zhaolei on 2018/7/19
 */
public class AcceptEventHandler extends EventHandler {

    private Selector selector;

    public AcceptEventHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void handle(Event event) {
        if (event.getType() != EventType.ACCEPT) return;
        Event readEvent = new Event(EventType.READ, event.getInputSource());
        selector.addEvent(readEvent);
    }
}

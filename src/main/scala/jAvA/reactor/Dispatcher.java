package jAvA.reactor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaolei on 2018/7/19
 */
public class Dispatcher {
    Map<EventType, EventHandler> eventHandlerMap = new ConcurrentHashMap<>();
    Selector selector;

    public Dispatcher(Selector selector) {
        this.selector = selector;
    }

    public void registerEventHandler(EventType type, EventHandler handler) {
        eventHandlerMap.put(type, handler);
    }

    public void removeEventHandler(EventType eventType) {
        eventHandlerMap.remove(eventType);
    }

    public void handlerEvents() {
        dispatcher();
    }

    private void dispatcher() {
        while (true) {
            List<Event> events = selector.select();
            events.forEach(e -> {
                EventHandler eventHandler = eventHandlerMap.get(e.getType());
                eventHandler.handle(e);
            });
        }

    }

}

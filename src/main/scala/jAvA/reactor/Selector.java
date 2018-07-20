package jAvA.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zhaolei on 2018/7/19
 */
public class Selector {

    private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
    private final Object lock = new Object();

    public void addEvent(Event event) {
        boolean success = eventQueue.offer(event);
        if (success) {
            synchronized (lock) {
                lock.notify();
            }
        }
    }

    public List<Event> select() {
        return select(0);
    }

    private List<Event> select(long timeout) {
        if (timeout > 0 && eventQueue.isEmpty()) {
            synchronized (lock) {
                if (eventQueue.isEmpty()) {
                    try {
                        lock.wait(timeout);
                    } catch (InterruptedException ignored) {

                    }
                }
            }
        }

        ArrayList<Event> events = new ArrayList<>();
        eventQueue.drainTo(events);
        return events;
    }
}

package jAvA.reactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zhaolei on 2018/7/19
 */
public class Acceptor implements Runnable {
    private  int port;
    private Selector selector;

    private BlockingQueue<InputSource> sourceQueue = new LinkedBlockingQueue<>();

    public Acceptor(int port, Selector selector) {
        this.port = port;
        this.selector = selector;
    }

    public void addQueue(InputSource source) {
        sourceQueue.offer(source);
    }

    public int getPort() {
        return port;
    }

    @Override
    public void run() {
        while (true) {
            InputSource source = null;
            try {
                source = sourceQueue.take();
            }catch (InterruptedException ignored){

            }

            if (source == null) continue;
            Event acceptEvent = new Event(EventType.ACCEPT,source);
            selector.addEvent(acceptEvent);
        }
    }
}

package jAvA.reactor;

/**
 * Created by zhaolei on 2018/7/19
 */
public class Server {
    private Selector selector = new Selector();
    Dispatcher eventLooper = new Dispatcher(selector);
    Acceptor acceptor;

    public Server(int port){
         acceptor = new Acceptor(port, selector);
    }

    public void start(){
        eventLooper.registerEventHandler(EventType.ACCEPT,new AcceptEventHandler(selector));
        eventLooper.registerEventHandler(EventType.READ,new ReadEventHandler());
        new Thread(acceptor,"acceptor - " + acceptor.getPort()).start();
        eventLooper.handlerEvents();
    }
}

package jAvA.reactor;

/**
 * Created by zhaolei on 2018/7/19
 */
public class Event {
    private EventType type;
    private InputSource inputSource;

    public Event(EventType type, InputSource inputSource) {
        this.type = type;
        this.inputSource = inputSource;
    }

    public Event() {
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public InputSource getInputSource() {
        return inputSource;
    }

    public void setInputSource(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type=" + type +
                ", inputSource=" + inputSource +
                '}';
    }
}

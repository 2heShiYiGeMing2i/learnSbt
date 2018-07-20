package jAvA.reactor;

/**
 * Created by zhaolei on 2018/7/19
 */
public class InputSource {
    private long id;
    private Object data;

    public InputSource(long id, Object data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "InputSource{" +
                "id=" + id +
                ", data=" + data +
                '}';
    }
}

package part15;

/**
 * Created by zhaolei on 2018/2/8
 */
public class FFF {
    private Son s = new Son();
    public void test() {
        s.getM();
    }
}

interface Grand {
    int G = 10;
}

interface Father extends Grand {
    int F = 20;

}

class Me implements Father {
    private int M = 30;

    public int getM() {
        return M;
    }
}

class Son extends Me {
    int S = 40;

    @Override
    public int getM() {
        return super.getM();
    }
}

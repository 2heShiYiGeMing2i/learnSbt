package partthree;

/**
 * Created by zhaolei on 2018/1/9
 */
public class Test {
    private static Greeting greeting = new Greeting();

    public static void main(String[] args) {
        Greeting x = greeting;
        Greeting y = x;
        System.err.println(x == y);
        Greeting z = greeting;
        System.err.println(x == z);

        greeting.sss();

    }
}

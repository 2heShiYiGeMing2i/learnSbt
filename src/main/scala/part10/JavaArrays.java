package part10;

/**
 * Created by zhaolei on 2018/2/6
 */
public class JavaArrays {
    public static void main(String[] args) {
        Number[] array2 = new Integer[]{
                1, 2, 3}; // 通过编译
        array2[2] = 3.14; // 通过编译，但会在运行时抛出错误
    }
}

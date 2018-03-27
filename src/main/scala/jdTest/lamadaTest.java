package jdTest;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Created by zhaolei on 2018/3/22
 */
public class lamadaTest {
    public static void main(String[] args) {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5);
        integers.forEach(integer -> {
            if (integer == 4) {
                return;
            }
            System.out.println(333);
        });
    }
}



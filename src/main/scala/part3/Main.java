package part3;

import java.util.Scanner;

/**
 * Created by zhaolei on 2018/1/24
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String next = scanner.next();
            char[] insert = insert(next);
            System.out.println(fast(insert));
        }
    }

    private static int fast(char[] tmp) {
        if (tmp == null) {
            return 0;
        }
        int[] p = new int[tmp.length];
        int mx = 0, id = 0, resLen = 0;
        for (int i = 0; i < tmp.length; i++) {
            p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
            while (i - p[i] >= 0 && i + p[i] < tmp.length && tmp[i + p[i]] == tmp[i - p[i]])
                ++p[i];
            if (mx < i + p[i]) {
                mx = i + p[i];
                id = i;
            }
            if (resLen < p[i]) {
                resLen = p[i];
            }
        }
        return resLen - 1;
    }

    private static final char MARK = '#';

    private static char[] insert(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        char[] chars = new char[length % 2 == 0 ? length * 2 + 1 : length * 2];
        chars[0] = MARK;
        for (int ch = 1; ch < chars.length - 1; ch++) {
            chars[ch] = str.charAt(ch / 2);
            ch++;
            chars[ch] = MARK;
        }
        return chars;
    }

}



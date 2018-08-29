package codeWar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DRoot {
    public static int digital_root(int n) {
        char[] str = (n + "").toCharArray();
        int sum = 0;
        while (str.length > 1) {
            sum = 0;
            for (char c : str) {
                int dig = (int) c - (int) '0';
                sum += dig;
            }
            str = (sum + "").toCharArray();
            if (str.length == 1) return sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(digital_root1(16));
        System.out.println(digital_root1(456));

        System.out.println(digital_root(16));
        System.out.println(digital_root(456));
    }

    public static int digital_root1(int n) {
        return n != 0 && n % 9 == 0 ? 9 : n % 9;
    }
}

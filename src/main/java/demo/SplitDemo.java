package demo;

import java.util.Arrays;
import java.util.List;

/**
 * demo
 *
 * @author jimmy
 * @date 2019-05-09
 */
public class SplitDemo {
    public static void main(String[] args) {
        sout("1");
        sout("");
        sout("1,");
        sout("1,2");

        System.out.println("".split(",",1).length);
    }

    private static void sout(String str) {
        System.out.println("====================================");
        System.out.println(Arrays.toString(str.split(",")));
        System.out.println(str.split(",").length);

        System.out.println(Arrays.toString(str.split(",", 1)));
        System.out.println(str.split(",",1).length);

        System.out.println(Arrays.toString(str.split(",", -1)));
        System.out.println(str.split(",",-1).length);
        System.out.println("====================================");
    }
}

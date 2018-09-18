package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量池存放在方法区，存储的是对象引用
 * 对象实例依然放在堆中
 * -verbose:gc -Xmx1M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * OOM java heap space
 */
public class JMMStringDemo {
    static String base = "string";

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }

    }
}

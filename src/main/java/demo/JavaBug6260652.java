package demo;

import jvm.SubClass;
import jvm.SuperClass;

import java.util.Arrays;
import java.util.List;

public class JavaBug6260652 {

    public static void test1() {
        List<String> list = Arrays.asList("abc");
        System.out.println(list.getClass());

        Object[] objArray = list.toArray();
        System.out.println(objArray.getClass());
        objArray[0] = new Object();
    }

    public static void main(String[] args) {
        String[] strArray = new String[10];
        Object[] objArray = strArray;
        objArray[0] = new Object();
    }
}

package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -Xmx1M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class OOMDemo {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        List<byte[]> arr =new ArrayList<>();
        int i =0;
        while(true){
            arr.add(new byte[_1MB]);
            i++;
            System.out.println(">>>>>>>>>>>>>>>"+i);
        }
    }
}

package jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * -verbose:gc -Xmx1M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class OOMMapDemo{
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args){
        Map<byte[],Object> map= new HashMap<>();
        while(true){map.put(new byte[_1MB],null);}
    }
}
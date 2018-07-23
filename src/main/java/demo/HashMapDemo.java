package demo;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("数学",2);
        map.put("化学",8);
        map.put("hello",1);
        System.out.println(map.get("数学"));
        System.out.println(map.get("化学"));
        System.out.println(map.get("hello"));
    }
}

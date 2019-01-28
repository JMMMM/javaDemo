package jvm;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<String, String>();
//        Map<String,String> map = new HashMap<String, String>();
//        map.put(null,"");//NullPointerException
//        map.put("",null);//NullPointerException
        List<String> ls = new ArrayList<String>();
        ls.add("a");
        ls.add("b");
        ls.add("c");
        ls.add("d");
        for(String key:ls){
            ls.remove(key);
        }
    }
}

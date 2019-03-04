package demo;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        WeakReference wr = new WeakReference(obj);
        System.out.println(wr.get());
        obj = null;
        Thread.sleep(5 * 1000);
        System.out.println(wr.get());
        HashMap map = new HashMap<String, Integer>();
        System.out.println(map.put("1", 1));
        System.out.println(map.put("1", 2));
    }
}

package demo;

import java.lang.ref.WeakReference;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        WeakReference wr = new WeakReference(obj);
        System.out.println(wr.get());
        obj=null;
        Thread.sleep(5*1000);
        System.out.println(wr.get());

    }
}

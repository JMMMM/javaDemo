package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDomain29 {
    public static AtomicInteger aiRef = new AtomicInteger();

    public void addNum() {
        System.out.println(Thread.currentThread().getName() + "加100后的结果：" + aiRef.addAndGet(100));
        aiRef.getAndAdd(1);
    }
}

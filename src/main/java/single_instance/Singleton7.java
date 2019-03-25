package single_instance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * https://www.jianshu.com/p/b302123532b4
 *
 * 这是lambda表达式在单例模式下的坑，可以感受一下
 */
public class Singleton7 {
    private static final Singleton7 instance = new Singleton7();

    private Singleton7() {
        long startTime = System.currentTimeMillis();
        System.out.println("new Singleton7");
        connect();
        System.out.println("cost:" + (System.currentTimeMillis() - startTime) + "ms");
    }

    private static void connect() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("hello world");
            countDownLatch.countDown();
        }).start();
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println(instance);
    }
}

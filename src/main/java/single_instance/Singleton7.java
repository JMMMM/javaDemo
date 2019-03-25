package single_instance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * https://www.jianshu.com/p/b302123532b4
 * lambda表达式会在Singleton7中创建一个静态的方法，
 * 在添加了static关键字的字段中，JVM会在启动时加载该字段，
 * 在加载过程中，就已经sleep 10s了，后面的其他类都在等待这10s，
 * 这10s超时后，才继续加载后续的类
 */
public class Singleton7 {
    private static final Singleton7 instance = new Singleton7();

    private Singleton7() {
        init();
    }

    private void init() {
        long startTime = System.currentTimeMillis();
        System.out.println("new Singleton7");
        connect();
        System.out.println("cost:" + (System.currentTimeMillis() - startTime) + "ms");
    }

    private static void connect() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread t1 = new Thread(() -> {
            System.out.println("hello world");
            countDownLatch.countDown();
        });
        t1.start();
        /**
         * 打开这行代码，其实就是确保t1线程顺利执行。
         * 但是在类加载过程中，由于当前类还没有加载完
         * 线程需要等待当前类加载完才能执行该方法
         */
//        try {
//            Thread.sleep(1*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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

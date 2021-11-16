package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程池的研究学习
 */
public class ThreadPoolExecuteDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecute = new ThreadPoolExecutor(2, 3, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(1));
        Future a = threadPoolExecute.submit(()->{});
        threadPoolExecute.execute(() -> {
            while (true) {
                System.out.println("hello");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
        threadPoolExecute.execute(() -> {
            while (true) {
                System.out.println("hello2222222");
            }
        });
//        threadPoolExecute.shutdown();
        threadPoolExecute.execute(() -> {
            while (true) {
                System.out.println("hello3333333");
            }
        });
        ReentrantLock lock = new ReentrantLock();
        threadPoolExecute.execute(() -> {
            while (true) {
                System.out.println("hello4444444");
            }
        });
        Thread.sleep(3000);
        /**
         * 此处shutdownNow并没有实际生效，由于代码中都没有可以interrupted的点
         * 所以即使shutdownNow依然没法马上结束线程池
         * 如果线程中没有sleep 、wait、Condition、定时锁等应用, interrupt()方法是无法中断当前的线程的。
         * 所以，ShutdownNow()并不代表线程池就一定立即就能退出，它可能必须要等待所有正在执行的任务都执行完成了才能退出。
         */
        threadPoolExecute.shutdownNow();
    }
}

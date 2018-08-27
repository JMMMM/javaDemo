package demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecuteDemo {
    public static void main(String[] args) throws InterruptedException {
        new Thread().start();
        ThreadPoolExecutor threadPoolExecute = new ThreadPoolExecutor(2, 3, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(1));
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
        Thread.sleep(3000);
        /**
         * 此处shutdownNow并没有实际生效，由于代码中都没有可以interrupted的点
         * 所以即使shutdownNow依然没法马上结束线程池
         */
        threadPoolExecute.shutdownNow();
    }
}

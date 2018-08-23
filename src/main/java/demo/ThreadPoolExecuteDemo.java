package demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecuteDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecute = new ThreadPoolExecutor(2, 3, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(1));
        threadPoolExecute.execute(() -> {
            while (true) {
                System.out.println("hello");
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
        threadPoolExecute.shutdown();
    }
}

package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo1 {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecute = new ThreadPoolExecutor(2, 3, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(1));


        threadPoolExecute.execute(() -> {
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        threadPoolExecute.execute(() -> {
//            try {
//                Thread.sleep(2 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        threadPoolExecute.execute(() -> {
//            try {
//                Thread.sleep(2 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        threadPoolExecute.execute(() -> {
//            try {
//                Thread.sleep(2 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        Thread.sleep(5 * 1000);
//        threadPoolExecute.execute(() -> {
//        });
    }
}

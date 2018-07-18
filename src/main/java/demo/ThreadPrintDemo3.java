package demo;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

class ThreadPrintDemo3 {
    volatile int num = 0;
    boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            new ThreadPrintDemo3().job();
        }
    }

    private void job() throws InterruptedException {

        final List<Integer> result = new Vector<>();

        final CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(() -> {
            for (; 100 > num; ) {
                if (!flag && (num == 0 || ++num % 2 == 0)) {
                    result.add(num);
                    flag = true;
                }
            }
            latch.countDown();
        }
        );

        Thread t2 = new Thread(() -> {
            for (; 100 > num; ) {
                if (flag && (++num % 2 != 0)) {
                    result.add(num);
                    flag = false;
                }
            }
            latch.countDown();
        }
        );

        t1.start();
        t2.start();

        latch.await();
        for (int i = 0; i <= 100; i++) {
            assert result.get(0) == i;
        }
        System.out.println("====================done!");
    }
}

package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过Condition实现奇偶输出
 */
public class ThreadDemo2 {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static int i = 0;
    private static List<Integer> rs = new ArrayList<>(100);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Ji());
        Thread t2 = new Thread(new Ou());
        t1.start();
        t2.start();
        boolean flag = false;
        t1.join();
        t2.join();

        for (int i = 0; i <= 100; i++) {
            flag = rs.get(i) == i;
            if (!flag) break;
        }
        if (flag) {
            System.out.println("没问题!");

        } else {
            System.out.println("出问题!");

        }
    }

    static class Ji implements Runnable {
        @Override
        public void run() {
            while (i <= 100) {
                try {
                    lock.lock();
                    if (i % 2 == 1) {
                        rs.add(i);
                        System.out.println("ji:" + i);
                        i++;
                        condition.signal();
                    } else {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    static class Ou implements Runnable {

        @Override
        public void run() {
            while (i <= 100) {
                try {
                    lock.lock();
                    if (i % 2 == 0) {
                        rs.add(i);
                        System.out.println("ou:" + i);
                        i++;
                        condition.signal();
                    } else {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

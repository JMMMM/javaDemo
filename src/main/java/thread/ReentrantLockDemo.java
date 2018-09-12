package thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ReentrantLockDemo.A());
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(new ReentrantLockDemo.B());
        t2.start();
    }

    static class A implements Runnable{

        @Override
        public void run() {
            lock.lock();
            lock.lock();//重入
        }
    }
    static class B implements Runnable{

        @Override
        public void run() {
            lock.lock();
            lock.lock();
        }
    }
}

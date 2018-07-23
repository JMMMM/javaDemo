package demo;

/**
 * 这个代码并不能达到奇偶数轮流输出的效果
 * 仔细阅读代码还是会有可能出现死锁的情况，这种方式时错误的
 */
public class Demo {

    private static Object lock = new Object();

    //奇数
    static class OddNum implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 99; i += 2) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    lock.notify();
                }
            }
        }
    }

    //偶数
    static class EvenNum implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 100; i += 2) {
                synchronized (lock) {
                    System.out.println(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new OddNum()).start();

        new Thread(new EvenNum()).start();
    }
}

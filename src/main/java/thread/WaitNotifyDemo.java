package thread;

/**
 * 知识点：
 * 1。当线程没有start时，执行notify时，对lock没有任何效果
 * 2。当线程start了，未进入wait时，先执行了notify，执行wait时会直接跳出
 * 3。当线程执行了wait，只有被notify后才会跳出wait。
 */
public class WaitNotifyDemo {

    private static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                lock.notify();
            }
        }).start();

    }
}

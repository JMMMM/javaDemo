package single_instance;

/**
 * 线程安全，但是方法锁性能不够好
 */
public class Singleton2 {
    private static Singleton2 _INSTANCE;
    //这里用的是类锁，锁住了getInstance方法，也是导致性能不够好的主要原因。
    static synchronized Singleton2 getInstance() {
        if (_INSTANCE == null) _INSTANCE = new Singleton2();
        System.out.println("instance");
        return _INSTANCE;
    }

    static synchronized int a() {
        while (true) {
            if (_INSTANCE != null) break;
            else {
                System.out.println("continue");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return 100;
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> Singleton2.a()).start();
        Thread.sleep(1000);
        new Thread(() -> Singleton2.getInstance()).start();
    }
}

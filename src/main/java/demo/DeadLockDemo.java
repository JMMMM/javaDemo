package demo;

public class DeadLockDemo {
    private static Object lockA = new Object();
    private static Object lockB = new Object();

    private static volatile boolean threadARunning = false;
    private static volatile boolean threadBRunning = false;

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (lockA) {
                threadARunning = true;
                while (!threadBRunning) ;
                System.out.println(" waitting lock b ");
                synchronized (lockB) {
                    System.out.println("thread a ");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (lockB) {
                threadBRunning = true;
                while (!threadARunning) ;
                System.out.println(" waitting lock a ");
                synchronized (lockA) {
                    System.out.println("thread b ");
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}


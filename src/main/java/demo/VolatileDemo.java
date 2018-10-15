package demo;

public class VolatileDemo {
    private static volatile int a = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    a++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) threads[j].yield();
        System.out.println(a);
    }
}

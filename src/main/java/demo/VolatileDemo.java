package demo;

public class VolatileDemo {
    private static volatile int a = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    a++;
                }
            });
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) threads[j].join();
        System.out.println(a);
    }
}

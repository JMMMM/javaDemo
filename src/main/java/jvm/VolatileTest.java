package jvm;

/**
 * 使用Volatile虽然能保证变量读取时会最新值，但是计算后回存到主存的时候，该值已经不是最新的了。
 * javap -c VolatileTest //.class
 */
public class VolatileTest {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) Thread.yield();
        System.out.println(race);
    }
}

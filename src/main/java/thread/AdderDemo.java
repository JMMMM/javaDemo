package thread;

import java.util.concurrent.atomic.LongAdder;

public class AdderDemo {
    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        System.out.println(longAdder.longValue());
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j <= 10; j++) {
                    longAdder.add(1);
                    try {
                        Thread.sleep(1*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println(longAdder);
    }
}

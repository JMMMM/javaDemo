package thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalOOm {
    public static void main(String[] args) {
        new Thread(new Thread1()).start();
    }
}


class Thread1 implements Runnable {
    private static ThreadLocal<byte[]> threadLocal1 = new ThreadLocal<>();


    @Override
    public void run() {
        byte[] result = new byte[1024 * 1024];
        new Thread(new Thread2(result)).start();
        System.gc();
        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        List<byte[]> list = new ArrayList<>();
        list.add(result);
        System.out.println(">>>>>>>>>>>"+1);
        while (true) {
            list.add(new byte[1024 * 1024]);
            System.out.println(">>>>>>>>>>>"+1);
        }
    }
}

class Thread2 implements Runnable {

    private byte[] storage;

    public Thread2(byte[] storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        System.out.println(storage.length);
    }
}

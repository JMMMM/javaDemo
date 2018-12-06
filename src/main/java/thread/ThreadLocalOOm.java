package thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalOOm {
    public static void main(String[] args) throws InterruptedException {
        Person p = new Person();
        new Thread(new Thread2(p)).start();
        Thread.sleep(2 * 1000);
        System.out.println(p.name);
    }
}

class Person {
    public byte[] name;

    public Person() {
        name = new byte[1024 * 1024];
    }

    @Override
    public void finalize() {
        System.out.println("person finalize...");
    }

}


class Thread2 implements Runnable {
    private ThreadLocal<Person> threadLocal1 = new ThreadLocal<>();

    public Thread2(Person person) {
        threadLocal1.set(person);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + threadLocal1.get());
        threadLocal1.remove();
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
    }
}

package thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalOOm {
    public static void main(String[] args) {
        new Thread(new Thread1()).start();
    }
}

class Person {
    private byte[] name;

    public Person() {
        name = new byte[1024 * 1024];
    }

    @Override
    public void finalize() {
        System.out.println("person finalize...");
    }

}

class Thread1 implements Runnable {

    @Override
    public void run() {
        new Thread(new Thread2()).start();
        System.gc();
        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
        List<Person> list = new ArrayList<>();
        list.add(new Person());
    }
}

class Thread2 implements Runnable {
    private static ThreadLocal<Person> threadLocal1 = new ThreadLocal<>();

    private Person person;

    public Thread2() {
        this.person = new Person();
        threadLocal1.set(person);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + person);
        threadLocal1.remove();
    }
}

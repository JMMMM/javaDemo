package thread.thread_local;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Demo4 {

    public static void main(String[] args) {
        new MyThread4().start();
    }
}

class MyThread4 extends Thread {

    private List<ThreadLocal<Person>> list = new ArrayList<>();

    @Override
    public void run() {
        for (int i = 0; i < 8; i++) {
            ThreadLocal threadLocal = new ThreadLocal();
            threadLocal.set(new Person());
            list.add(threadLocal);
        }
        System.out.println("hello");
    }
}
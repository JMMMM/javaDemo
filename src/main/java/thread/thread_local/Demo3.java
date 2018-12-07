package thread.thread_local;

import java.io.IOException;

/**
 * 为什么没有GC？？内存泄漏案例
 */
public class Demo3 {
    public static void main(String[] args) {
        new ChildThread3().start();
    }
}

class MyThreadLocal3 extends ThreadLocal {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("MyThreadLocal3 gc ed ");
    }
}

//DEBUG模式
class ChildThread3 extends Thread {
    private ThreadLocal<Person> threadLocal = new ThreadLocal();

    @Override
    public void run() {
        Person person = new Person();
        threadLocal.set(person);

        System.out.println("threadlocal gc start ");
//        threadLocal.remove();这个去掉可以试试
        threadLocal = null;
        person = null;
        System.gc();
        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("threadlocal gc end ");

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package thread.thread_local;


/**
 * 错误的ThreadLocal用法，偷换线程
 */
public class Demo2 {
    public static void main(String[] args) {
        Person p = new Person();
        ChildThread runnable = new ChildThread(p);
        new Thread(runnable).start();
//        System.out.println("main:" + runnable.get());
    }

}


class ChildThread implements Runnable {
    private ThreadLocal<Person> threadLocal = new ThreadLocal<>();

    public ChildThread(Person p) {
        threadLocal.set(p);
    }

    @Override
    public void run() {
        System.out.println("Child:" + threadLocal.get());
    }

    public Person get() {
        return threadLocal.get();
    }
}
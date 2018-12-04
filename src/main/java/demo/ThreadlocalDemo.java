package demo;

public class ThreadlocalDemo {

    static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            Integer a = 100;
            threadLocal.set(a);
            a = null;
            System.gc();
            System.out.println(threadLocal.get());
        }).start();
    }
}

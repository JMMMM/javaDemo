package demo;

/**
 * 用于测试类加载过程中，只允许一个线程对类进行初始化，其余的线程必须等待
 * 初始化阶段主要任务：
 * 1：为静态变量赋值
 * 2：执行static代码块
 * 目前代码是失败的。
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> new ThreadTestObj());
        t1.setName("thread1");

        Thread t2 = new Thread(() -> new ThreadTestObj());
        t2.setName("thread2");

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

}


class ThreadTestObj {
    private static int age = 15;

    static {
        System.out.println(Thread.currentThread().getName() + "执行静态代码块。。。。start");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public ThreadTestObj() {
        System.out.println(Thread.currentThread().getName() + "ThreadTestObj start ......");
        System.out.println(Thread.currentThread().getName() + "ThreadTestObj end ......");
    }
}

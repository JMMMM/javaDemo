package demo;

import java.util.concurrent.TimeUnit;

/**
 * demo
 *
 * @author jimmy
 * @date 2019-08-27
 * 这个demo主要是对守护线程的学习理解
 * The Java Virtual Machine exits when the only threads running are all daemon threads.
 * 当 JVM 中不存在任何一个正在运行的非守护线程时，则 JVM 进程即会退出。
 */
public class DeamonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //模拟非守护线程不退出的情况
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("I am running .....");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        TimeUnit.SECONDS.sleep(2);

        System.out.println("The main thread ready to exit .. ..");
    }
}

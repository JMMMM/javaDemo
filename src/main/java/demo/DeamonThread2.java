package demo;

import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.TimeUnit;

/**
 * demo
 *
 * @author jimmy
 * @date 2019-08-27
 * 这里和DeamonThread对比，当线程设置为守护线程时，在jvm结束的时候会自动结束守护线程
 */
public class DeamonThread2 {
    public static void main(String[] args) throws InterruptedException {
        //设置钩子线程，在JVM推出时输出日志
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The JVM exit success !!!");
        }));
        //在主线程中new 一个非守护线程
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("I am running ......");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("The main thread ready to exit......");
    }
}

package demo;

import redis.clients.util.IOUtils;

import java.util.Scanner;


/**
 * demo
 *
 * @author jimmy
 * @date 2019-08-28
 * 看一篇文章想
 */
public class IOTest {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        Thread t = new Thread(() -> {
            try {
                String input = in.nextLine();
                System.out.println(input);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                in.close();
            }
        }, "输入输出");

        t.start();
        Thread.sleep(100);
        System.out.println(t.getState());
    }
}

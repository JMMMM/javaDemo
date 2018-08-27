package face;

import java.util.ArrayList;
import java.util.List;

/**
 * 当flag不是volatile 时，会出现对flag变量不可见的情况
 * 程序多运行几次，会出现 set flag true 但没有其他任何输出的情况
 * 需要运行多次
 */
public class TestVisibility {
    public static boolean flag = false;

    public static void main(String[] args) {
        List<Thread> thdList = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if (flag) {
                            // 多运行几次，可能并不会打印出来也可能会打印出来
                            // 如果不打印，则表示Thread看到的仍然是工作内存中的flag
                            // 可以尝试将flag变成volatile再运行几次看看
                            System.out.println(Thread.currentThread().getId() + " is true now");
                        }
                    }
                }
            });
            t.start();
            thdList.add(t);
        }

        flag = true;
        System.out.println("set flag true");

        // 等待线程执行完毕
        try {
            for (Thread t : thdList) {
                t.join();
            }
        } catch (Exception e) {

        }
    }
}

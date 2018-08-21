package face;

import java.util.ArrayList;
import java.util.List;

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

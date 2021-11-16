package qdama;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desctiption <Template>
 * @Author wujiaming
 * @Date 2021/5/18
 */
public class StackOuterOfMemoryDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        while(true){
            threads.add(new Thread());
            Thread.sleep(100);
            System.out.println(threads.size());
        }
    }
}

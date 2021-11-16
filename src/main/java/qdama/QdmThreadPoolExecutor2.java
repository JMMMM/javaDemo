package qdama;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Desctiption 增加等待队列
 * @Author wujiaming
 * @Date 2021/5/19
 */
public class QdmThreadPoolExecutor2 {
    //核心线程数
    private int coreThreadSize;

    //最大线程数
    private int maxThreadSize;

    //核心线程
    private List<Work2> works = new ArrayList<>(maxThreadSize);

    private BlockingQueue<Runnable> blockingQueue;

    public QdmThreadPoolExecutor2(int coreThreadSize, int maxThreadSize) {
        this.coreThreadSize = coreThreadSize;
        this.maxThreadSize = maxThreadSize;
        this.blockingQueue = new ArrayBlockingQueue(10);
    }

    //执行
    public void execute(Runnable runnable) throws InterruptedException {
        //小于核心线程数就创建Work对象，执行任务
        if (works.size() < coreThreadSize) {
            addWorker(runnable);
        } else if (blockingQueue.offer(runnable)) {
            System.out.println("添加队列成功");
        } else {
            if (works.size() < maxThreadSize) {
                addWorker(runnable);
            } else {
//                reject();
                System.out.println("满了");
            }
        }
    }

    private Work2 addWorker(Runnable runnable) {
        Work2 work = new Work2("work" + works.size(), runnable);
        work.thread.start();
        works.add(work);
        return work;
    }

    class Work2 implements Runnable {
        private static final int RUNNING = 1;
        private static final int RUNNABLE = 0;

        private volatile int state;
        public Thread thread;
        private volatile Runnable job;

        private String name;

        public Work2(String name, Runnable firstTask) {
            this.state = RUNNABLE;
            this.job = firstTask;
            this.thread = new Thread(this);
            this.name = name;
        }

        @Override
        public void run() {
            while (this.job != null || (this.job = take()) != null) {
                synchronized (this) {
                    this.state = RUNNING;
                    job.run();
                    job = null;
                    this.state = RUNNABLE;
                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//            works.remove(this);
                }
            }
        }

        /**
         * 这里希望拿任务的时候，如果大于核心线程数返回null
         *
         * @return
         * @throws InterruptedException
         */
        private Runnable take() {
            boolean is_bigger_than_core_thread_size = works.size() > coreThreadSize;
            Runnable runnable = null;
            try {
                runnable = is_bigger_than_core_thread_size ? blockingQueue.poll(100, TimeUnit.SECONDS) : blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (runnable == null && is_bigger_than_core_thread_size) {
                return null;
            }

            return runnable;
        }

    }

}

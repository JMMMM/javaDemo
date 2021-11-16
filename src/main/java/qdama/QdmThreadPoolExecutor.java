package qdama;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desctiption 自定义线程池开发
 * 功能描述：
 * 创建线程池时，只需要制定核心线程数，当线程池满时，添加新任务时阻塞。
 * @Author wujiaming
 * @Date 2021/5/18
 */
public class QdmThreadPoolExecutor {
    //核心线程数
    private int coreThreadSize;
    //核心线程
    private List<Work> works = new ArrayList<>();


    public QdmThreadPoolExecutor(int coreThreadSize) {
        this.coreThreadSize = coreThreadSize;
    }

    //执行
    public void execute(Runnable runnable) throws InterruptedException {
        //小于核心线程数就创建Work对象，执行任务
        if (works.size() < coreThreadSize) {
            Work work = new Work("work" + works.size(), runnable);
            work.thread.start();
            works.add(work);
        } else {
            while (true) {
                for (Work work : works) {
                    if (work.addJob(runnable)) {
                        return;
                    }
                }
                Thread.sleep(1000);
            }
        }
    }

    class Work implements Runnable {
        private static final int RUNNING = 1;
        private static final int RUNNABLE = 0;

        private volatile int state;
        public Thread thread;
        private volatile Runnable job;

        private String name;

        public Work(String name, Runnable firstTask) {
            this.state = RUNNABLE;
            this.job = firstTask;
            this.thread = new Thread(this);
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (this.job != null) {
                        synchronized (this) {
                            this.state = RUNNING;
                            job.run();
                            job = null;
                            this.state = RUNNABLE;
                        }
                    } else {
                        System.out.println(this.name + ":空闲");
                    }
                    Thread.sleep(2 * 1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        public boolean addJob(Runnable job) {
            synchronized (this) {
                if (this.state == RUNNABLE && this.job == null) {
                    System.out.println(this.name + ":添加工作=========" + System.currentTimeMillis());
                    this.job = job;
                    return true;
                }
                System.out.println(this.name + ":正在工作" + System.currentTimeMillis());
                return false;
            }
        }

    }
}

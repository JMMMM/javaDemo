package qdama;

/**
 * @Desctiption <Template>
 * @Author wujiaming
 * @Date 2021/5/19
 */
public class QdmWorker implements Runnable {
    private static final int RUNNING = 1;
    private static final int FREE = 0;


    private Runnable job;
    private int state;
    private Thread t;

    public QdmWorker(Runnable job) {
        this.job = job;
        t = new Thread(this);
    }


    @Override
    public void run() {
        while (true) {
            state = RUNNING;
            this.job.run();
            state = FREE;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addJob(Runnable job) {
        if (state == FREE) {
            this.job = job;
            return true;
        }
        return false;
    }
}

package thread;

/**
 * 使用Actomic出现的问题（多运行几次）
 */
public class ActomicStudyDemo extends Thread {
    private ThreadDomain29 td;

    public ActomicStudyDemo(ThreadDomain29 td) {
        this.td = td;
    }

    public void run() {
        td.addNum();
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadDomain29 td = new ThreadDomain29();
        ActomicStudyDemo[] mt = new ActomicStudyDemo[5];
        for (int i = 0; i < mt.length; i++) {
            mt[i] = new ActomicStudyDemo(td);
        }
        for (int i = 0; i < mt.length; i++) {
            mt[i].start();
        }
        Thread.sleep(1000);
        System.out.println(ThreadDomain29.aiRef.get());
    }
}

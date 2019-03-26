package thread;

public class InterruptedDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        System.out.println("Interruted When sleep");
                        System.out.println("再次调用isInterrupted方法：" + this.isInterrupted());
                    }
                }
            }
        };
        t1.start();
        //确保线程执行
        Thread.sleep(2 * 1000);
        t1.interrupt();
    }
}

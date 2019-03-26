package thread;

public class InterruptedDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (this.isInterrupted()) {
                        System.out.println("线程中断");
                        break;
                    }
                }
                System.out.println("已跳出循环，线程中断!");
            }
        };
        t1.start();
        Thread.sleep(2 * 1000);
        t1.interrupt();
    }
}

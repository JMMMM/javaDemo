package thread;


class ThreadPrintDemo1 {
    public static void main(String[] args) {
        new ThreadPrintDemo1().job();
    }

    public void job() {
        final ThreadPrintDemo1 demo2 = new ThreadPrintDemo1();
        Thread t1 = new Thread(demo2::print1);
        Thread t2 = new Thread(demo2::print2);

        t1.start();
        t2.start();
    }


    public synchronized void print2() {
        for (int i = 1; i < 100; i += 2) {
            System.out.println(i);
            this.notify();
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
    }

    public synchronized void print1() {
        for (int i = 0; i <= 100; i += 2) {
            System.out.println(i);
            this.notify();
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
    }
}
package demo;


public class SynchronizedDemo {


    synchronized public void m1(){
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m1");
        }
    }

    synchronized public void m2() {
        System.out.println("m2");
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo demo =  new SynchronizedDemo();
        Thread t1 = new Thread(()->demo.m1());
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(()->demo.m2());
        t2.start();
    }
}

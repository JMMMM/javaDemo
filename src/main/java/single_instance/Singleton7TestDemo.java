package single_instance;

public class Singleton7TestDemo {
    private static long startTime = System.currentTimeMillis();
    private final static S1 s1 = new S1();

    public static void main(String[] args) {
        System.out.println("还没有开始");
        new Thread(s1).start();
        System.out.println(System.currentTimeMillis() - startTime);
    }
}


class S1 implements Runnable {
    public S1() {
        System.out.println("s1");
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void s2() {
        System.out.println("s2");
    }

    @Override
    public void run() {
        s2();
    }
}

class S2 {
    public S2(String printStr) {
        System.out.println(printStr);
    }
}
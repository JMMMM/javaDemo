package thread;

public class ThreadPriceDemo1Test {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        for (long i = 0; i < 10000; i++) {
            new ThreadPrintDemo1().job();
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}

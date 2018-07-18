package thread;

/**
 * volatile 重排列命令测试
 */
public class ThreadDemo1 {

    static Object object = null;
    static boolean inited = false;

    static class Thread1 implements Runnable {

        @Override
        public void run() {
            object = new Object();
            inited = true;
        }
    }


    static class Thread2 implements Runnable {

        @Override
        public void run() {
            while (!inited) {
            }
            System.out.println(object.toString());
        }
    }

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
    }

}

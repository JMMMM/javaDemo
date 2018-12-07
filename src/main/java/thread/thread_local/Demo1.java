package thread.thread_local;

/**
 * GC线程优先级比较低，在通知JVM gc时，
 * JVM只是标记该队长为gc状态，要过一会才GC
 * 这里通过注释Thread.sleep观察现象
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Person p = new Person();
        p= null;
        System.gc();
//        Thread.sleep(1000);
    }
}



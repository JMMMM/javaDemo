package single_instance;

/**
 * version 6
 * 这个是由于jvm对于内部类是延迟加载的，这里是更好的单例模式
 * 这个单例模式线程安全，是懒汉模式
 */
public class Singleton6 {

    private Singleton6() {
        System.out.println("init");
    }

    //内部类只有在调用的时候才会加载到jvm，未调用之前是不占用内存的。
    private static class SingletonHolder {
        private static final Singleton6 _INSTANCE = new Singleton6();
    }

    public static Singleton6 getInstance() {
        return SingletonHolder._INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println("start");
        Singleton6.getInstance();
        System.out.println("end");

    }
}

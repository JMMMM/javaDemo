package single_instance;

/**
 * 基础单例，线程不安全
 */
public class Singleton1 {
    private static Singleton1 _INSTANCE;

    static Singleton1 getInstance() {
        //这个if线程不安全
        if (_INSTANCE == null) _INSTANCE = new Singleton1();
        return _INSTANCE;
    }
}

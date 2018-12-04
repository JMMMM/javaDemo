package single_instance;

/**
 * 正确的双重锁校验
 */
public class Singleton5 {
    private static volatile Singleton5 _INSTANCE;

    static Singleton5 getInstance() {
        if (_INSTANCE == null) {
            synchronized (Singleton5.class) {
                if (_INSTANCE == null) {
                    _INSTANCE = new Singleton5();
                }
            }
        }
        return _INSTANCE;
    }
}

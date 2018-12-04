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
                    /**
                     * 这里会出现问题，下面的代码实际上执行了3个步骤
                     * 1，分配空间
                     * 2，初始化对象。
                     * 3，将对象指向刚分配的内存空间
                     *
                     * 编译器为了性能，可能会让2，3步重排序，出现问题，
                     * 于是当顺序是1、3、2时，_INSTANCE == null为flase，
                     * 但是实际上，初始化方法还没有执行，导致获取的可能是个没有初始化完的对象。
                     */
                    _INSTANCE = new Singleton5();
                }
            }
        }
        return _INSTANCE;
    }
}

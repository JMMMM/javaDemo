package single_instance;

/**
 * 性能好，但是浪费内存。
 */
public class Singleton3 {

    //这一块通过类加载的时候就已经产生了，这里就是浪费内存的点
    private static final Singleton3 _INSTANCE = new Singleton3();

    static Singleton3 getInstance(){
        return _INSTANCE;
    }
    private Singleton3(){
        System.out.println("ini");
    }

    public static void main(String[] args) {
        System.out.println("hello world start ");
        Singleton3.getInstance();
        System.out.println("hello world end");

    }
}

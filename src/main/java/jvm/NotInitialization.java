package jvm;

/**
 * 类加载
 * 每次执行只运行一个case
 */
class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}

class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init");
    }
}

public class NotInitialization {
    public static void main(String[] args) {
        //case 1:当子类调用父类属性时，不会初始化子类。
//        System.out.println(SubClass.value);
        //case 2:-XX:+TraceClassLoading
        SuperClass[] sca = new SuperClass[10];
    }
}

package demo;

/**
 * demo
 *
 * @author jimmy
 * @date 2019-05-15
 */
public class Demo7 {
    public static void main(String[] args) {
        System.out.println(Demo7Test.test);
    }
}

class Demo7Test {
    static Demo7Test test = new Demo7Test();

    static {
        System.out.println("static");
    }

    static int a = 10;

    {
        System.out.println("normal");
        System.out.println(a);
    }

}
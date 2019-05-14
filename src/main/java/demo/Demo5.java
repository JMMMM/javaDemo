package demo;

/**
 * demo
 *
 * @author jimmy
 * @date 2019-05-14
 */
public class Demo5 {
    public static final int tmp = One.tmp;

    public static void main(String[] args) {
        System.out.println("hello world " + tmp);
    }

}

class One {
    static Integer tmp = 1;
}

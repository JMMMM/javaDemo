package face;

/**
 * String 的classLoader为null
 * 这是由于BootstrapClassLoader使用的是c++编写的，
 * 在java中找不到这个classLoader，所以为null
 */
public class StringDemo {
    public static void main(String[] args) {
        new StringBuilder();
        new StringBuffer();
        ClassLoader classLoader = String.class.getClassLoader();
        ClassLoader classLoader1 = Integer.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader1);
    }

}

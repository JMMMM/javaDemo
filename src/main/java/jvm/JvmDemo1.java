package jvm;

/**
 * jdk1.6与1.7的差别
 * jdk1.6是把变量整个复制到常量池中，地址不同结果是：false,false
 * 而jdk1.7是把引用复制到常量池中，引用地址相同结果是：true：false
 */
public class JvmDemo1 {
    public static void main(String[] args) {
        /**
         * ==比较符属于地址比较
         * StringBuilder 返回的是new String("计算机软件")创建在堆中，
         * 当执行intern方法时，查看方法区中的常量池这个字符串的引用地址，如果没有，就往常量池中添加引用
         * 于是str1.intern()==str1
         */

        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1 );
        /**
         * 同上，由于"java"字符串本来就存在常量池中，于是str.intern会出现读取常量池中的引用。
         * 所以常量池的引用与堆的引用判断为false
         *
         */
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}

package demo;

public class EqualsDemo {
    public static void main(String[] args) {
        System.out.println(Long.valueOf("9999999").equals(new Long(9999999L)));
        System.out.println(Long.valueOf("9999999") == new Long(9999999L));
        System.out.println(Long.valueOf("1") == new Long(1L));
        System.out.println(1L == new Long(1L));
    }
}

package demo;

public class IntegerDemo {
    public static void main(String[] args) {
        int a = 300;
        Integer b = 300;
        System.out.println(a==b);
        System.out.println(b==a);

        Integer c = 300;
        System.out.println(b==c);

        Integer d = 100;
        Integer e = 100;
        System.out.println(d==e);


    }
}

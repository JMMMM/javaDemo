package demo;

public class StringDEo {

    private static String add(String ex){
        ex = ex+ "World";
        return ex;
    }

    public static void main(String[] args) {
        String a = "hello";
        System.out.println(add(a));
        System.out.println(a);
    }
}

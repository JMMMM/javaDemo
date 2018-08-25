package demo;

import java.lang.reflect.Field;

public class SwapDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        int a = 1;
        int b = 2 ;
        swap1(a,b);
        System.out.println(a+","+b);
        Integer c = 444;
        Integer d = 555;
        swap2(c,d);
        System.out.println(c+","+d);
        Integer e = 1;
        Integer f = 2;
        swap3(e,f);
        System.out.println(e+","+f);
    }

    public static void swap1(int a ,int b ){
        b=2;
        a=1;
    }

    public static void swap2(Integer a ,Integer b ){
        b=2;
        a=1;
    }

    public static void swap3(Integer a ,Integer b ) throws NoSuchFieldException, IllegalAccessException {
        int temp = a.intValue();
        Field field= a.getClass().getDeclaredField("value");
        field.setAccessible(true);
        field.setInt(a,b);
        field.setInt(b,temp);
    }
}

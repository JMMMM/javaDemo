package demo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestClass {
    private static int m;

    public static void main(String[] args) {
        System.out.println(excep());
    }


    private static int excep() {
        try {
            m = 0;
//            throw new Exception();
            return m;
        } catch (Exception ex) {
            m = 1;
            System.out.println("catch");
            return m;
        } finally {
            m = 2;
            System.out.println("finally");
//            return m;
        }
    }
}

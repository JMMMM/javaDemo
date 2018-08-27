package demo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestClass {
    private int m;
    public int inc(){return m + 1;}

    public static void main(String[] args) {
        double   f   =   111234.5585;
        BigDecimal   b   =   new BigDecimal(f);
        double   f1   =   b.setScale(0, RoundingMode.DOWN).doubleValue();
        System.out.println(f1);
    }
}

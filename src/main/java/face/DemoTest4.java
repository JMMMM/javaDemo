package face;

import java.math.BigDecimal;

public class DemoTest4 {

    public static void main(String[] args) {
        BigDecimal hundred = new BigDecimal(100);
        Double dou1 = Double.valueOf(12.60);
        BigDecimal b1 = new BigDecimal("12.60");
        BigDecimal b2 = new BigDecimal(dou1);
        System.out.println(b1.multiply(hundred).intValue());
        System.out.println(b2.multiply(hundred).intValue());

    }

}

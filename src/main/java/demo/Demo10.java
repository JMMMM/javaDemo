package demo;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * demo
 *
 * @author JM
 * @date 2019-10-30
 */
public class Demo10 {
    public static void main(String[] args) throws IllegalAccessException {
        OrderSettleAnalyseShopHourDto dto = new OrderSettleAnalyseShopHourDto();
        dto.setHours(1);
        dto.setTotalPspAmt(BigDecimal.TEN);
        dto.setTotalCustQty(10L);
        Field[] fields = OrderSettleAnalyseShopHourDto.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
//            field.set();
            System.out.println(field.get(dto));
        }
    }
}


class OrderSettleAnalyseShopHourDto {
    private int hours;
    private BigDecimal totalPspAmt;
    private Long totalCustQty;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public BigDecimal getTotalPspAmt() {
        return totalPspAmt;
    }

    public void setTotalPspAmt(BigDecimal totalPspAmt) {
        this.totalPspAmt = totalPspAmt;
    }

    public Long getTotalCustQty() {
        return totalCustQty;
    }

    public void setTotalCustQty(Long totalCustQty) {
        this.totalCustQty = totalCustQty;
    }
}

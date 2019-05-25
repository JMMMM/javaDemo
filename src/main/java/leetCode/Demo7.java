package leetCode;

/**
 * leetCode
 *
 * @author jimmy
 * @date 2019-05-25
 */
public class Demo7 {
    public int reverse(int x) {
        int y = 0;
        int i = 10;
        boolean flag = x < 0;
        if (flag) x = x * -1;
        while (x >= 10) {
            y = y * i;
            y += (x % 10);
            x = x / 10;
            if (y < 0) {
                return 0;
            }
        }
        if ((Integer.MAX_VALUE / 10) < y) {
            return 0;
        } else {
            if (Integer.MAX_VALUE / 10 > y && Integer.MAX_VALUE % 10 < x) {
                return 0;
            }
            y = y * 10 + x;
            return flag ? -1 * y : y;
        }
    }

    public static void main(String[] args) {
        int rs = new Demo7().reverse(900000);
        System.out.println(rs);
    }
}

package leetCode;

/**
 * leetCode
 *
 * @author jimmy
 * @date 2019-05-25
 */
public class Demo7 {
    public static int reverse(int x) {
        int result = 0;

        while (x != 0) {
            // x % 10 可以取到 x 最后一位的值，即此时 pop 是 x 的最后一位，也就是新值的第一位
            int pop = x % 10;
            // x 的位数少了最后一位
            x = x / 10;

            // 由于后续运算 result = result * 10 + pop
            // 如果 result 的值大于 Integer.MAX_VALUE / 10 ，那么一定会溢出
            // 如果 result 的值等于 Integer.MAX_VALUE / 10，那么 pop 的值如果大于 Integer.MAX_VALUE % 10 也会溢出
            // 相反的，result 的值小于 Integer.MIN_VALUE / 10 ，那么一定会溢出
            // 如果 result 的值等于 Integer.MIN_VALUE / 10，那么 pop 的值如果小于于 Integer.MIN_VALUE % 10 也会溢出

            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
                result = 0;
                return result;
            } else if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)) {
                result = 0;
                return result;
            }

            result = result * 10 + pop;
        }
        return result;
    }

    public static void main(String[] args) {
        int rs = new Demo7().reverse(900000);
        System.out.println(rs);
    }
}

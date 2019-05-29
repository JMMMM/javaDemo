package leetCode;

/**
 * leetCode
 *
 * @author jimmy
 * @date 2019-05-29
 */
public class Demo8 {
    public int myAtoi(String str) {
        char[] charArray = str.toCharArray();
        int result = 0;
        int zf = 0;
        boolean isStart = false;
        for (char c : charArray) {
            if (c >= '0' && c <= '9') {
                isStart = true;
                if (zf == 0) {
                    zf = 1;
                }
                int toInt = c - '0';
                if (zf == -1) {
                    if (zf * result < Integer.MIN_VALUE / 10 || zf * result == Integer.MIN_VALUE / 10 && toInt > 8) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    if (result > Integer.MAX_VALUE / 10 || result == Integer.MAX_VALUE / 10 && toInt > 7) {
                        return Integer.MAX_VALUE;
                    }
                }
                result = result * 10 + toInt;
            } else if (c == '-') {
                isStart = true;
                if (zf == 0) {
                    zf = -1;
                } else {
                    return zf * result;
                }
            } else if (c == '+') {
                isStart = true;
                if (zf == 0) {
                    zf = 1;
                } else {
                    return zf * result;
                }
            } else if (c == ' ' && !isStart) {
                continue;
            } else {
                return zf * result;
            }
        }
        return zf * result;
    }

    public static void main(String[] args) {
        Demo8 demo = new Demo8();
        int result = demo.myAtoi("   -42");
        System.out.println(result);
    }
}

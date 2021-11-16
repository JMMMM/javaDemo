package qdama;

/**
 * @Desctiption <Template>
 * @Author wujiaming
 * @Date 2021/5/5
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        A a = new A();
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            nums[index] = nums[i];
            index++;
        }
        System.out.println(nums);
    }
}

class A {
    boolean a = false;
}
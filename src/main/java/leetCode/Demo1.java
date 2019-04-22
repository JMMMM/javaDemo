package leetCode;

public class Demo1 {
    public int[] twoSum(int[] nums, int target) {
        int[] rs = new int[2];

        for (int i = 0; i <= nums.length-1; i++) {
            rs[0] = i;
            for (int j = i + 1; j <= nums.length-1; j++) {
                if ((nums[i] + nums[j]) == target) {
                    rs[1] = j;
                    return rs;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        new Demo1().twoSum(nums, 6);
    }
}

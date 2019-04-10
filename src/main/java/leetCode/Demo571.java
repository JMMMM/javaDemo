package leetCode;

public class Demo571 {
    public static void main(String[] args) {
        int[] nums = {1,7,3,6,5,6};
        System.out.println(pivotIndex(nums));
    }

    public static int pivotIndex(int[] nums) {
        int sum = 0;
        int leftSum=0;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        for (int i = 0;i<nums.length;i++) {
            sum -= nums[i];
            if (sum == leftSum) {
                index = i;
                break;
            }
            leftSum += nums[i];

        }
        return index;
    }
}

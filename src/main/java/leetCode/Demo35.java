package leetCode;

import java.nio.file.Files;

public class Demo35 {
    public static void main(String[] args) {
        int[] nums = {3,4,5,6,7,8};
        searchInsert(nums, 6);
    }

    public static int searchInsert(int[] nums, int target) {
        return search(0, nums.length-1, nums, target);
    }

    public static int search(int start, int end, int[] nums, int target) {
        if (end - start <= 1) {
            return nums[end] == target ? end : nums[start] == target ? start : 0;
        }
        int middle = (end + start) >> 1;
        if (nums[middle] == target) return middle;
        else if (nums[middle] < target) {
            return search(middle + 1, end, nums, target);
        } else {
            return search(start, middle - 1, nums, target);
        }
    }
}

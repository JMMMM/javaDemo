package leetCode;

public class Demo4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int totalLength = nums1.length + nums2.length;
        if (totalLength == nums1.length) {
            return totalLength % 2 == 1 ? nums1[totalLength >> 1] : ((double) nums1[totalLength >> 1] + nums1[(totalLength >> 1) - 1]) / 2;
        }

        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] > nums2[0]) {
                int temp = nums1[i];
                nums1[i] = nums2[0];
                nums2[0] = temp;
                int j = 1;
                while (j < nums2.length) {
                    if (nums2[j - 1] > nums2[j]) {
                        int temp2 = nums2[j - 1];
                        nums2[j - 1] = nums2[j];
                        nums2[j] = temp2;
                        j++;
                    } else {
                        break;
                    }
                }
            }
        }
        int mid = totalLength / 2;
        if (mid < nums1.length) {
            return totalLength % 2 == 1 ? (double) nums1[mid] : ((double) nums1[mid] + nums1[mid - 1]) / 2;
        } else {
            if (mid - nums1.length == 0) {
                return totalLength % 2 == 1 ? (double) nums2[0] : ((double) nums1[nums1.length-1] + nums2[0]) / 2;
            } else {
                return totalLength % 2 == 1 ? (double) nums2[1] : ((double) nums2[0] + nums2[1]) / 2;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1};
        int[] nums2 = {2,3, 4,5};
        System.out.println(new Demo4().findMedianSortedArrays(nums1, nums2));
    }
}

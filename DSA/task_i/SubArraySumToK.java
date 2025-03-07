package task_i;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumToK {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 0};
        int length = findSubArraySumToK(arr, 4);
        System.out.println("Longest subsequent " + length);
        length = findAllSubArraysWithGivenSum(arr, 6);
        System.out.println("Longest subsequent " + length);
    }

    static int findSubArraySumToK(int[] arr, int k) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int currentSum = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                currentSum += arr[j];
                if (currentSum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int findAllSubArraysWithGivenSum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int preSum = 0, cnt = 0;
        map.put(0, 1); // Setting 0 in the map.

        for (int j : nums) {
            // add current element to prefix Sum:
            preSum += j;
            // Calculate x-k:
            int remove = preSum - k;

            // Add the number of sub-arrays to be removed:
            cnt +=  map.getOrDefault(remove, 0);

            // Update the count of prefix sum in the map.
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return cnt;
    }
}

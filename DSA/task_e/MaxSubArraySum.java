package task_e;

public class MaxSubArraySum {
    static int maxSubArraySum(int[] numbers, int windowLength) {
        if (numbers.length < windowLength) {
            System.out.println("Invalid input: Array length is less than the window size.");
            return -1;
        }
        int maxSum, windowSum = 0;
        int len = numbers.length;

        for (int i = 0; i < windowLength; i++) {
            windowSum += numbers[i];
        }

        maxSum = windowSum;

        for (int i = windowLength; i < len; i++) {
            windowSum = windowSum - numbers[i - windowLength] + numbers[i];
            if (maxSum < windowSum) maxSum = windowSum;
        }
        return maxSum;
    }

    static int maxSubArraySum(int[] arr) {
        if (arr == null) return -1;
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
            if (sum < 0) { //reset and start calculating for next index
                sum = 0;
            }
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {15, 2, 4, -80, 9, 5, 10, 23};
        int subArrayLength = 5;
        int res = maxSubArraySum(arr, subArrayLength);
        int maxSum = maxSubArraySum(arr);
        System.out.println("Max Sum :" + res);
        System.out.println("Max Sum :" + maxSum);
    }
}

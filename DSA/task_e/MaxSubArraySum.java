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

    public static void main(String[] args) {
        int[] arr = {15, 2, 4, 80, 9, 5, 10, 23};
        int subArrayLength = 5;
        int res = maxSubArraySum(arr, subArrayLength);
        System.out.println("Max Sum :" + res);
    }
}

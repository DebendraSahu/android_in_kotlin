package task_e;

import java.util.ArrayList;

public class SubArraySum {
    static ArrayList<Integer> subArraySum(int[] numbers, int target) {
        ArrayList<Integer> subArray = new ArrayList<>(2);
        int len = numbers.length;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum += numbers[j];
                if (sum == target) {
                    subArray.add(i + 1);
                    subArray.add(j + 1);
                    return subArray;
                }
            }
        }

        subArray.add(-1);
        return subArray;
    }

    public static void main(String[] args) {
        int[] arr = {15, 2, 4, 8, 9, 5, 10, 23};
        int target = 23;
        ArrayList<Integer> res = subArraySum(arr, target);

        for (int ele : res)
            System.out.print(ele + " ");
    }
}

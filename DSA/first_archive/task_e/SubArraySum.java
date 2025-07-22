package first_archive.task_e;

import java.util.ArrayList;

public class SubArraySum {
    static ArrayList<Integer> subArraySum(int[] numbers, int target) {
        ArrayList<Integer> subArray = new ArrayList<>(2);
        int len = numbers.length;
        int sum = 0, s = 0, e = 0;

        for (int i = 0; i < len; i++) {
            sum += numbers[i];
            if (sum >= target) {
                e = i;
                while (sum > target && s < e) {
                    sum -= numbers[s];
                    s++;
                }

                if (sum == target) {
                    subArray.add(s + 1);
                    subArray.add(e + 1);
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

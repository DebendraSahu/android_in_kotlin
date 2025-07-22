package first_archive.task_i;

import java.util.HashSet;

public class TwoSum {
    public static void main(String[] args) {
        int[] arr = {0, -1, 2, -3, 1};
        int target = -2;
        if (twoSum(arr, target)) System.out.println("true");
        else System.out.println("false");
        int[] l = {1000000000, 1000000000, 1000000000, 1000000000};
        int m = 0;
        for (int i : l) {
            System.out.println(m);
            if (Integer.MIN_VALUE - m > i) {       // 2000000000
                System.out.println("Small value ");// 2147483647
            }
            m = m + i;
        }
        System.out.println(m);
    }

    static boolean twoSum(int[] arr, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int j : arr) {
            int complement = target - j;
            if (set.contains(complement)) {
                return true;
            }
            set.add(j);
        }
        return false;
    }
}

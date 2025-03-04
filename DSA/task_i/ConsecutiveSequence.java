package task_i;

import java.util.HashSet;

public class ConsecutiveSequence {
    public static void main(String[] args) {
        int[] arr = { 2, 6, 1, 9, 4, 5, 3 };
        int length = longestConsecutiveSubsequent(arr);
        System.out.println("Longest subsequent " + length);
    }

    static int longestConsecutiveSubsequent(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int a : arr) {
            set.add(a);
        }

        int longestSubsequent = 1;

        for (int j : arr) {
            if (!set.contains(j - 1)) {
                int cnt = 1;
                int x = j;
                while (set.contains(x + 1)) {
                    x = x + 1;
                    cnt = cnt + 1;
                }
                longestSubsequent = Math.max(longestSubsequent, cnt);
            }
        }
        return longestSubsequent;
    }
}

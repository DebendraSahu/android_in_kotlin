package first_archive.task_d;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, result);
        return result;
    }

    private static void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            current.add(nums[i]);
            backtrack(nums, current, used, result);
            current.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        List<List<Integer>> permutations = permute(numbers);
        System.out.println("Permutations:");
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }
    }
}

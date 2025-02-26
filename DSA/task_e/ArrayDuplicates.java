package task_e;

import java.util.ArrayList;
import java.util.List;

public class ArrayDuplicates {
    static List<Integer> findDuplicates(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            int num = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (num == arr[j]) {
                    duplicates.add(num);
                }
            }
        }
        return duplicates;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 1, 5};
        List<Integer> duplicates = findDuplicates(arr);
        System.out.println("Duplicates in array:");
        for (int num : duplicates) {
            System.out.print(num + " ");
        }

    }
}

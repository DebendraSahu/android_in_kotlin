package task_e;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    static List<Integer> findDuplicatesUsingSet(int[] arr) {
        Set<Integer> lookup = new HashSet<>();
        List<Integer> duplicates = new ArrayList<>();
        for (int num : arr) {
            if (lookup.contains(num)) {
                duplicates.add(num);
            } else {
                lookup.add(num);
            }
        }
        return duplicates;
    }


    //only works if the elements are lesser that size
    static List<Integer> findDuplicatesUsingArraySpace(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        int len = arr.length;
        for (int a : arr) {
            int index = a % len;
            arr[index] += len;
        }

        for (int i = 0; i < len; i++) {
            if (arr[i] / len >= 2) duplicates.add(i);
        }

        return duplicates;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 1, 5};
        List<Integer> duplicates = findDuplicatesUsingSet(arr);
        System.out.println("Duplicates in array:");
        for (int num : duplicates) {
            System.out.print(num + " ");
        }
    }
}

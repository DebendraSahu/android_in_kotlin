package task_e;

import java.util.ArrayList;

public class MissingNumber {
    static int missingNumber(int[] numbers) {
        int len = numbers.length;
        boolean[] contains = new boolean[len + 1];
        contains[0] = true;

        for (int num : numbers) {
            contains[num - 1] = true;
        }

        for (int i = 0; i < contains.length; i++) {
            if (!contains[i]) return i + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 3};
        int res = missingNumber(arr);

        System.out.print("Missing number is :" + res);
    }
}

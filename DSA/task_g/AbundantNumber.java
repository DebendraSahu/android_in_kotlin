package task_g;

import java.util.ArrayList;
import java.util.List;

public class AbundantNumber {
    public static void main(String[] args) {
        List<Integer> abundantNumbers = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            if (checkAbundantNumber(i)) {
                abundantNumbers.add(i);
            }
        }
        System.out.println("Abundant Numbers from 0 to 2000:");
        for (int num : abundantNumbers) {
            System.out.print(num + ", ");
        }
    }

    private static boolean checkAbundantNumber(int number) {
        if (number < 2) return false;
        int sum = 1;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                if (i * i != number) sum = sum + i + number / i;
                else sum = sum + i;
            }
            if (sum > number) return true;
        }
        return false;
    }
}

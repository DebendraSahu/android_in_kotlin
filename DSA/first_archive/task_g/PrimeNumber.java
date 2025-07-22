package first_archive.task_g;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {
    public static void main(String[] args) {
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            if (isPrimeNumber(i)) {
                primeNumbers.add(i);
            }
        }
        System.out.println("Prime Numbers from 0 to 2000:");
        for (int num : primeNumbers) {
            System.out.print(num + ", ");
        }
    }

    private static boolean isPrimeNumber(int number) {
        if (number < 2) return false; // 0 and 1 are not prime


        int itr = (int) Math.sqrt(number);

        for (int i = 2; i <= itr; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

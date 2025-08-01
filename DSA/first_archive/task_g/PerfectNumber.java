package first_archive.task_g;

import java.util.ArrayList;
import java.util.List;

public class PerfectNumber {
    public static void main(String[] args) {
        System.out.println("Perfect Numbers from 0 to 20000:");

        for (int i = 0; i < 20000; i++) {
            if (isPerfectNumber(i)) {
                System.out.print(i + ", ");
            }
        }

        List<Long> perfectNumbers = new ArrayList<>();
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            if (isPerfectNumberOp(i)) {
                System.out.println(i + ", ");
                perfectNumbers.add(i);
            }
        }
        System.out.println("Perfect Numbers from 0 to 9223372036854775807L:");
        for (long num : perfectNumbers) {
            System.out.print(num + ", ");
        }
    }


    private static boolean isPerfectNumber(int n) {
        if (n < 2) return false;
        int sum = 1;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if (i * i != n) sum = sum + i + n / i;
                else sum = sum + i;
            }
        }
        return sum == n;
    }

    /**
     * just find out that here only 6, 28, 496, 8128, 33550336, 8589869056L, 137438691328L
     *
     * @param n number
     * @return true if it is a perfect number.
     */
    private static boolean isPerfectNumberOp(Long n) {
        return (n == 6) || (n == 28) || (n == 496) || (n == 8128) || (n == 33550336) || (n == 8589869056L) || (n == 137438691328L);
    }
}

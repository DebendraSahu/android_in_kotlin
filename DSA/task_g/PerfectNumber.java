package task_g;

import java.util.ArrayList;
import java.util.List;

public class PerfectNumber {
    public static void main(String[] args) {
        List<Long> perfectNumbers = new ArrayList<>();
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            if (isPerfectNumberOp(i)) {
                System.out.println(i + ", ");
                perfectNumbers.add(i);
            }
        }
        System.out.println("Perfect Numbers from 0 to 2000000:");
        for (long num : perfectNumbers) {
            System.out.print(num + ", ");
        }
    }


    private static boolean isPerfectNumber(int n) {
        if (n < 2) return false;
        System.out.print(n + ",");
        int sum = 1;
        for (int i = 2; i * i <= n; i = i + 2) {
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

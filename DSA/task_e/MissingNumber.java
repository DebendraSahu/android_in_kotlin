package task_e;


public class MissingNumber {


    // Cons: It can run into integer overflow problems if the numbers or 𝑛 are large,
    // because the computed sum might exceed the range of the integer type.
    static int missingNumber(int[] numbers) {
        int len = numbers.length + 1;
//       Sn = (n * (n + 1)) / 2 Gp
        int expectedTotal = (len * (1 + len)) / 2;
        int actualTotal = 0;
        for (int num : numbers) {
            actualTotal += num;
        }
        return expectedTotal - actualTotal;
    }

    static int missingNumber(int[] a, int n) {
        int xor1 = 0, xor2 = 0;

        for (int i = 0; i < n - 1; i++) {
            xor2 = xor2 ^ a[i]; // XOR of array elements
            xor1 = xor1 ^ (i + 1); //XOR up to [1...N-1]
        }
        xor1 = xor1 ^ n; //XOR up to [1...N]

        return (xor1 ^ xor2); // the missing number
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 3, 7, 8, 9, 10, 11};
        int res = missingNumber(arr, 11);
        System.out.print("Missing number is :" + res);
    }
}

package task_e;


public class MissingNumber {
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


    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 3, 7, 8, 9, 10, 11};
        int res = missingNumber(arr);

        System.out.print("Missing number is :" + res);
    }
}

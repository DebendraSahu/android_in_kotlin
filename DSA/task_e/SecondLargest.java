package task_e;

public class SecondLargest {

    static int getSecondLargest(int[] arr) {
        int max = Integer.MIN_VALUE, secMax = Integer.MIN_VALUE;
        for (int a : arr) {
            if (max < a) {
                secMax = max;
                max = a;
            } else if (secMax < a && a != max) {
                secMax = a;
            }
        }
        return secMax;
    }

    public static void main(String[] args) {
        int[] arr = {12, 35, 1, 10, 35, 1};
        int secondLargest = getSecondLargest(arr);
        System.out.println("SecondLarge  number :" + secondLargest);
    }
}

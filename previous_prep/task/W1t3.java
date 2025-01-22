package task.week1;

/**
 * **Task 3:** Create a Java program that uses an array to store and print the first 10 natural numbers.
 */
public class W1t3 {
    public static void main(String[] args) {
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = i + 1; // Store the first 10 natural numbers
        }

        for (int i = 0; i < a.length; i++) {
            System.out.println("sl no: " + i + " value: " + a[i]);
        }
    }
}

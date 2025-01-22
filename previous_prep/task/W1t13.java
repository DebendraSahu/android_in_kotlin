package task.week1;


import java.util.Arrays;
import java.util.List;

/**
 * **Task 13:** Write a program using Java Lambda expressions
 * to iterate through a list of integers and print only the even numbers.
 */
public class W1t13 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.stream()
                .filter(number -> number % 2 == 0)
                .forEach(System.out::println);
    }
}

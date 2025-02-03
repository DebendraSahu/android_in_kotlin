import java.util.Random;

public class ArrayApplications {
    public static void main(String[] args) {
        ArrayApplications applications = new ArrayApplications();
        int numbers[] = applications.createRandomArray(20);
        applications.printArrayItems(numbers);

        int randomItem = new Random().nextInt(20);
        int indexOfItem = applications.findElementIndexOfArray(numbers, numbers[randomItem]);
        System.out.println("Random items index : " + indexOfItem);

        randomItem = new Random().nextInt(100);
        applications.replaceElementIndexOfArray(numbers, randomItem, indexOfItem);
        System.out.println("Replaced item "+ randomItem);
        applications.printArrayItems(numbers);

        int newNumbers[] = applications.deleteElementAtIndex(numbers, randomItem);
        System.out.println("Deleted item: " + randomItem);
        applications.printArrayItems(newNumbers);
    }


    public int[] createRandomArray(int count) {
        int numbers[] = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            numbers[i] = random.nextInt(1000);
        }
        return numbers;
    }

    public void printArrayItems(int[] numbers) {
        System.out.println("Printing Elements: ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + ", ");
        }
        System.out.println(".");
    }

    public int findElementIndexOfArray(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public boolean replaceElementIndexOfArray(int[] numbers, int newValue, int index) {
        if (index > numbers.length)
            return false;
        numbers[index] = newValue;
        return true;
    }

    public int[] deleteElementAtIndex(int numbers[], int value) {
        int index = findElementIndexOfArray(numbers, value);
        if (index == -1) {
            System.out.println("Element not found");
            return numbers;
        }
        int length = numbers.length - 1;
        int newNumbers[] = new int[length];
        for (int i = 0; i < length; i++)
            if (i < index) {
                newNumbers[i] = numbers[i];
            } else {
                newNumbers[i] = numbers[i + 1];
            }

        return newNumbers;
    }
}

/**
 * Applications of Array Data Structure:
 * Arrays mainly have advantages like random access and cache friendliness over
 * other data structures that make them useful.
 * 
 * Below are some applications of arrays.
 * 
 * Storing and accessing data: Arrays store elements in a specific order and
 * allow constant-time O(1) access to any element.
 * Searching: If data in array is sorted, we can search an item in O(log n)
 * time. We can also find floor(), ceiling(), kth smallest, kth largest, etc
 * efficiently.
 * Matrices: Two-dimensional arrays are used for matrices in computations like
 * graph algorithms and image processing.
 * Implementing other data structures: Arrays are used as the underlying data
 * structure for implementing stacks and queues.
 * Dynamic programming: Dynamic programming algorithms often use arrays to store
 * intermediate results of subproblems in order to solve a larger problem.
 * Data Buffers: Arrays serve as data buffers and queues, temporarily storing
 * incoming data like network packets, file streams, and database results before
 * processing.
 */
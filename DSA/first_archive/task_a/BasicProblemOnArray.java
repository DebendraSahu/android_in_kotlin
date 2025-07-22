package first_archive.task_a;

import java.util.Arrays;

public class BasicProblemOnArray {
    private static final int ARRAY_LENGTH = 20;
    public static void main(String[] args) {
        ArrayApplications arrayApplications = new ArrayApplications();
        int nums[] = arrayApplications.createRandomArray(ARRAY_LENGTH);
        arrayApplications.printArrayItems(nums);

        printAlternates(nums);
        System.out.println("Searching for a Value");
        searchAValue(nums, nums[ARRAY_LENGTH - 2]);
        System.out.println("The largest number in array is " + findLargestNumber(nums));
        System.out.println("The largest number in array is " + largest(nums));
        System.out.println("The largest number in array is " + largestUseRec(nums));
        System.out.println("The Second largest number in array is " + findSecondLargest(nums));
        System.out.println("The Second largest number in array is " + findSecondLarge(nums));
        int arr[] = findLargestThrees(nums);
        System.out.println("The Three largest numbers");
        arrayApplications.printArrayItems(arr);
    }

    // #1 print alternets of array

    public static void printAlternates(int nums[]) {
        int length = nums.length;
        System.out.println("alternets of nums:");
        for (int i = 0; i < length; i += 2) {
            System.out.print(nums[i] + ", ");
        }
        System.out.println("");
    }

    // #2 search a value

    public static void searchAValue(int numbers[], int target) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                System.out.println("number Found at index: " + i);
                return;
            }
        }
        System.out.println("Number not found in array");
    }

    // #3 Find the largest value

    public static int findLargestNumber(int nums[]) {
        int largestNumber = nums[0];
        for (int idx = 1; idx < nums.length; idx++) {
            if (largestNumber < nums[idx]) {
                largestNumber = nums[idx];
            }

        }
        return largestNumber;
    }

    public static int largest(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }

    public static int findLargestRec(int nums[], int idx) {
        if (idx == nums.length - 1) {
            return nums[idx];
        }
        int recLarger = findLargestRec(nums, idx + 1);
        return Math.max(recLarger, nums[idx]);
    }

    public static int largestUseRec(int[] arr) {
        return findLargestRec(arr, 0);
    }

    // #4 Find the second largest

    public static int findSecondLargest(int nums[]) {
        int largest = findLargestNumber(nums);
        int secondLargest = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > secondLargest && nums[i] != largest) {
                secondLargest = nums[i];
            }
        }
        return secondLargest;
    }

    // another way

    public static int findSecondLarge(int nums[]) {
        Arrays.sort(nums);
        if (nums.length > 2)
            return (nums[nums.length - 2]);
        else
            return nums[0];
    }


    // #5 largest 3s

    public static int[] findLargestThrees(int nums[]) {
        int large = Integer.MIN_VALUE;
        int larger = Integer.MIN_VALUE;
        int largest = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > largest)  {
                large = larger;
                larger = largest;
                largest = num;
            } else if (num > larger && num != largest) {
                large = larger;
                larger = num;
            }  else if ((num > large) && (num != larger) && (num != largest)) {
                large = num;
            }
        }

        return new int[] {largest, larger, large};

    }

}

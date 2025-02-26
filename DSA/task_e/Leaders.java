package task_e;

import java.util.ArrayList;

public class Leaders {
    static ArrayList<Integer> leaders(int[] arr) {
        ArrayList<Integer> leaders = new ArrayList<>();
        int currentLeader = Integer.MIN_VALUE;
        int i = arr.length - 1;
        while (i >= 0) {
            int newLeader = arr[i];
            if (currentLeader <= newLeader) {
                leaders.addFirst(newLeader);
                currentLeader = newLeader;
            }
            i--;
        }
        return leaders;
    }

    public static void main(String[] args) {
        int[] arr = {10, 4, 2, 4, 1};// {16, 17, 4, 3, 5, 2};
        ArrayList<Integer> leaders = leaders(arr);
        System.out.println("Leaders in array are: ");
        for (int leads : leaders) {
            System.out.print(leads + " ");
        }
    }
}

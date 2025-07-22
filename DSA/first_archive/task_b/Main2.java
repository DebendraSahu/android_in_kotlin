package first_archive.task_b;


public class Main2 {
    public static void main(String[] args) {
        // Test Case 1: Linked list with a cycle
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // Cycle: -4 points back to 2

        DetectCycleInList DetectCycleInList = new DetectCycleInList();
        ListNode cycleStart = DetectCycleInList.detectCycle(node1);

        if (cycleStart != null) {
            System.out.println("Test Case 1: Cycle starts at node with value: " + cycleStart.val); // Expected: 2
        } else {
            System.out.println("Test Case 1: No cycle detected.");
        }

        // Test Case 2: Linked list without a cycle
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(2);

        node5.next = node6;
        node6.next = null; // No cycle

        cycleStart = DetectCycleInList.detectCycle(node5);

        if (cycleStart != null) {
            System.out.println("Test Case 2: Cycle starts at node with value: " + cycleStart.val);
        } else {
            System.out.println("Test Case 2: No cycle detected."); // Expected: No cycle
        }

        // Test Case 3: Single node without a cycle
        ListNode node7 = new ListNode(1);
        node7.next = null; // No cycle

        cycleStart = DetectCycleInList.detectCycle(node7);

        if (cycleStart != null) {
            System.out.println("Test Case 3: Cycle starts at node with value: " + cycleStart.val);
        } else {
            System.out.println("Test Case 3: No cycle detected."); // Expected: No cycle
        }
    }
}
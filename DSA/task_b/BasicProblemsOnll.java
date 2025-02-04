package task_b;

import task_b.IntLinkedList.Node;

public class BasicProblemsOnll {
    public void runAllProblems() {
        IntLinkedList list = new IntLinkedList();
        list.insertAtLast(201);
        list.insertAtFirst(20);
        list.insertAtFirst(23);
        list.insertAtFirst(22);
        list.insertAtFirst(74);
        list.insertAtFirst(56);
        list.insertAtLast(46);
        list.insertAtFirst(20);
        list.insertAtFirst(23);
        list.insertAtFirst(22);
        list.insertAtFirst(74);
        list.insertAtFirst(56);
        list.insertAtLast(46);
        list.insertAtFirst(20);
        list.insertAtFirst(23);
        list.insertAtFirst(22);
        list.insertAtFirst(74);
        list.insertAtFirst(56);
        list.insertAtLast(46);
        System.out.println("Values: " + list.toString());
        System.out.println("Middle value is: " + getTheMiddle(list.get(0)));
        System.out.println("Occurrence of 23 is: " + countOccurrence(list.get(0), 23));
        System.out.println("is List circular: " + isCircularList(list.get(0)));
        deleteK(list.get(0), 3);
        System.out.println("Values: " + list.toString());
    }

    public static Node deleteK(Node head, int k) {
        if (head == null || k <= 0) {
            return head; // Return the original head if input is invalid
        }
    
        Node curr = head;
        Node prev = null;
        int count = 0;
    
        while (curr != null) {
            count++;
            if (count % k == 0) {
                if (prev != null) {
                    prev.next = curr.next; // Skip the k-th node
                } else {
                    head = curr.next; // Update head if the first node is deleted
                }
            } else {
                prev = curr; // Move prev only if the current node is not deleted
            }
            curr = curr.next; // Move to the next node
        }
    
        return head; // Return the updated head
    }


    public static int getLengthOfList(Node head) {
        if (head == null) return 0;
        int count = 0;
        Node temp = head;
        while(temp != null) {
            temp = temp.next;
            count++;
        }

        return count;
        
    }
    public static int getTheMiddle(Node head) {
        int length = getLengthOfList(head);

        int middle = length / 2;

        Node mNode = head;

        while(middle > 0) {
            mNode = mNode.next;
            middle--;
        }
        return mNode.data;
    }

    public static int countOccurrence(Node head, int value) {
        if (head == null) return 0;
        int count = 0;
        Node temp = head;
        while(temp != null) {
            if (temp.data == value) count++;
            temp = temp.next;
        }

        return count;
    }

    public static boolean isCircularList(Node head) {
        if (head == null) return true;
        Node temp = head;
        Node headCopy = head;

        while(temp.next != headCopy) {
            if (temp == null || temp.next == null) return false;
            temp = temp.next;
        }

        return true;
    } 
}

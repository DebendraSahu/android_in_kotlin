package task_d;

// Java program to check if
// linked list is palindrome.

import java.util.Stack;

import task_b.DoubleLinkedList;

class Node {
    int value;
    Node next;

    Node(int d) {
        value = d;
        next = null;
    }
}

public class Palindrome {
    static boolean isPalindrome(Node head) {
        Node currNode = head;
        Stack<Integer> s = new Stack<>();

        while (currNode != null) {
            s.push(currNode.value);
            currNode = currNode.next;
        }

        while (head != null) {
            int c = s.pop();
            if (head.value != c) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // Recursive Function to check whether 
    // the list is palindrome
    static boolean isPalindromeRecur(Node end, Node[] start) {

        // base case
        if (end == null) return true;

        // Recursively check the right side.
        boolean right = isPalindromeRecur(end.next, start);

        // Compare the start and end nodes.
        boolean ans = right && start[0].value == end.value;

        // Update the start node 
        start[0] = start[0].next;

        return ans;
    }

    static boolean isPalindromeList(Node head) {

        // Set starting node to head
        Node[] start = new Node[]{head};

        return isPalindromeRecur(head, start);
    }

    static Node reverseList(Node head) {
        if (head == null || head.next == null) return head;
        Node curr = head, prev = null, front;

        while (curr != null) {
            front = curr.next;
            curr.next = prev;
            prev = curr;
            curr = front;
        }
        return prev;
    }


    static boolean isPalindromeUsingTwoPointerTech(Node head) {
        if (head == null || head.next == null) return true;

        Node fast = head, slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // reaches up to middle of the list
            fast = fast.next.next; //reaches up to last of the list
        }

        Node secondHalf = reverseList((fast == null) ? slow.next : slow);
        Node firstHalf = head;

        while (secondHalf.next != null) {
            if (secondHalf.value != firstHalf.value) {
                secondHalf = reverseList(secondHalf);
                return false;
            } else {
                secondHalf = secondHalf.next;
                firstHalf = firstHalf.next;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(1);

        boolean result = isPalindrome(head);

        System.out.println("is palindrome :" + (result ? "true" : "false"));
        System.out.println("is palindrome using recursion:" + (isPalindromeList(head) ? "true" : "false"));
        System.out.println("is palindrome using thw pointer:" + (isPalindromeUsingTwoPointerTech(head) ? "true" : "false"));
    }
}

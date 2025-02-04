package task_b;

import java.util.Random;

public class LinkedListApplications {




    public  Node insertAtEnd(Node head, int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return head;
        }

        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        return head;
    }

    public  Node insertAtStart(Node head, int value) {
        head = new Node(value, head);
        return head;
    }

    public Node createLinkedList(int count) {
        Random random = new Random();
        Node node = new Node(random.nextInt(200), null);
        for (int i = 0; i < count; i++) {
            node = new Node(random.nextInt(200), node);
        }
        return node;
    }

    public  Node insertAtPosition(Node head, int pos, int data) {
        if (pos < 1) {
            System.out.println("Invalid position!");
            return head;
        }

        if (pos == 1) {
            Node temp = new Node(data);
            temp.next = head;
            return temp;
        }

        // Traverse the list to find the node before the insertion point
        Node prev = head;
        int count = 1;
        while (count < pos - 1 && prev != null) {
            prev = prev.next;
            count++;
        }

        // If position is greater than the number of nodes
        if (prev == null) {
            System.out.println("Invalid position!");
            return head;
        }

        // Insert the new node at the specified position
        Node temp = new Node(data);
        temp.next = prev.next;
        prev.next = temp;

        return head;
    }

    public  Node removeFirstNode(Node head) {
        if (head == null)
            return null;

        // Move the head pointer to the next node
        head = head.next; //may be null
        return head;
    }

    public  Node removeLastNode(Node head) {
        if (head == null)
            return null;
        
        Node secondLastNode = head;

        if (secondLastNode.next == null) {
            head = null;
            return head;
        }

        while(secondLastNode.next.next != null) {
            secondLastNode = secondLastNode.next;
        }

        secondLastNode.next = null;
        return head;
    }

    public  Node removeSpecificNode(Node head, int position) {
        if (head == null || position < 1)
            return null;
        Node node = head;
        if (node.next == null) {
            return head;
        }
        
        int currentNodePosition = 0;
        while(currentNodePosition < position - 1 && node != null) {
            node = node.next;
            currentNodePosition++;
        }
        node = node.next.next;
        return head;

    } 

    public void printLinkedList(Node node) {
        System.out.println("Printing nodes: ");
        Node currentNode = node;
        while (currentNode != null) {
            System.out.print(currentNode.data + ", ");
            currentNode = currentNode.next;
        }
    }
}

package first_archive.task_f;

public class RemoveDuplicateNodes {
    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    private static String nodesInString(Node head) {
        Node temp = head;
        StringBuilder s = new StringBuilder();
        while (temp != null) {
            s.append(temp.data).append(" -> ");
            temp = temp.next;
        }
        s.append("end");
        return s.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(2);
        head.next.next = new Node(4);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Nodes before Removing duplicates:");
        System.out.println(nodesInString(head));

        head = removeDuplicateNodes(head);

        System.out.println("Nodes After Removing duplicates");
        System.out.println(nodesInString(head));
    }

    private static Node removeDuplicateNodes(Node head) {
        if (head == null) return head;
        Node temp = head;
        while (temp.next != null) {
            if (temp.data == temp.next.data) temp.next = temp.next.next;
            else temp = temp.next;
        }
        return head;
    }

}

package task_b;

public class CircularLinkedList {
    public class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public Node get(int index) {
        if (head == null || index > size || index < 0) {
            return null;
        }

        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public Node Find(int value) {
        Node node = head;
        while (node != null) {
            if (node.data == value) {
                return node;
            }
            if (node.next == head) {
                return null;
            }
            node = node.next;
        }
        return null;
    }

    public int insertAtFirst(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        tail.next = newNode;
        newNode.next = head;
        tail = newNode;
        size++;
        return size;
    }

    public int insertAtLast(int value) {
        if (size == 0) {
            return insertAtFirst(value);
        }
        Node newNode = new Node(value);
        tail.next = newNode;
        newNode.next = head;
        tail = newNode;
        size++;
        return size;
    }

    public int insert(int pos, int data) {
        if (pos <= 0) {
            return insertAtFirst(data);
        }
        if (pos >= size) {
            return insertAtLast(data);
        }

        Node prev = head;
        for (int i = 0; i < pos - 1; i++) { // Move to node BEFORE the insertion point
            prev = prev.next;
        }

        Node newNode = new Node(data, prev.next);
        prev.next = newNode;
        size++;
        return size;
    }

    public int removeFirst() {
        head = head.next;
        tail.next = head;
        if (head == null) {
            tail = null;
        }
        size--;
        return size;
    }

    public int removeLast() {
        if (head == null)
            return size;

        if (size <= 1) {
            return removeFirst();
        }

        Node secondLastNode = get(size - 2);
        tail = secondLastNode;
        tail.next = head;
        size--;
        return size;
    }

    public int remove(int index) {
        if (index == 0)
            return removeFirst();
        if (index == (size - 1))
            return removeLast();

        Node prevNode = get(index - 1);
        prevNode.next = prevNode.next.next;
        size--;
        return size;

    }

    @Override
    public String toString() {
        if (size == 0 || head == null)
            return "";
        Node currentNode = head;
        String nodeValues = "";
        do {
            if (currentNode == null)
                break;
            nodeValues = nodeValues + currentNode.data + " -> ";
            currentNode = currentNode.next;
        } while (currentNode != head);
        nodeValues += "HEAD";
        return nodeValues;
    }
}

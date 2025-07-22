package first_archive.task_b;

public class DoubleLinkedList {
    public class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
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
            node = node.next;
        }
        return null;
    }

    public int insertAtFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        newNode.prev = null;

        if (head != null)
            head.prev = newNode;
        head = newNode;
        if (tail == null)
            tail = head;
        size++;
        return size;
    }

    public int insertAtLast(int value) {
        if (size == 0) {
            return insertAtFirst(value);
        }
        Node newNode = new Node(value);
        tail.next = newNode;
        newNode.next = null;
        newNode.prev = tail;
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

        Node newNode = new Node(data, prev.next, prev);

        newNode.prev.next = newNode;
        newNode.next.prev = newNode;

        size++;
        return size;
    }

    public int removeFirst() {
        if (head == null) return size;
        head = head.next;
        head.prev = null;
        size--;
        if (size == 1) {
            tail = head;
        }
        return size;
    }

    public int removeLast() {
        if (head == null)
            return size;

        if (size <= 1)
            return removeFirst();
        tail = tail.prev;
        tail.next = null;

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
        prevNode.prev = prevNode; 
        size--;
        return size;

    }

    @Override
    public String toString() {
        if (size == 0)
            return "";
        Node currentNode = head;
        String nodeValues = "";
        while (currentNode != null) {
            nodeValues = nodeValues + currentNode.data + " -> ";
            currentNode = currentNode.next;
        }
        nodeValues += "END";
        return nodeValues;
    }

    public String toStringReverse() {
        if (size == 0)
            return "";
        Node currentNode = tail;
        String nodeValues = "";
        while (currentNode != null) {
            nodeValues = nodeValues + currentNode.data + " -> ";
            currentNode = currentNode.prev;
        }
        nodeValues += "START";
        return nodeValues;
    }
}

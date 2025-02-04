package task_b;

public class Main {
    public static void main(String[] args) {
        LinkedListApplications applications = new LinkedListApplications();
        Node node = applications.createLinkedList(20);
        applications.printLinkedList(node);
    }
}

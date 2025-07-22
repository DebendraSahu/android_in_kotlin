package first_archive.task_b;

public class Main {
    public static void main(String[] args) {
        linearLinkedList();
        doubleLinkedList();
        circularLinkedList();
        BasicProblemsOnll problems = new BasicProblemsOnll();
        problems.runAllProblems();
    }

    public static void circularLinkedList() {
        CircularLinkedList list = new CircularLinkedList();
        list.insertAtFirst(20);
        list.insertAtFirst(23);
        list.insertAtFirst(22);
        list.insertAtFirst(74);
        list.insertAtFirst(56);
        list.insertAtLast(41);
        list.insertAtLast(46);
        System.out.println("" + list.Find(23).data);
        System.out.println("" + list.get(3).data);
        System.out.println("Values: " + list.toString());
        list.insert(5, 98);
        // list.insert(8, 76);
        System.out.println("Values: " + list.toString());
        list.removeFirst();
        System.out.println("Values: " + list.toString());
        list.removeLast();
        System.out.println("Values: " + list.toString());
        // list.remove(0);
        // System.out.println("Values: " + list.toString());
        list.remove(5);
        System.out.println("Values: " + list.toString());
    }

    public static void doubleLinkedList() {
        DoubleLinkedList list = new DoubleLinkedList();
        list.insertAtFirst(20);
        list.insertAtFirst(23);
        list.insertAtFirst(22);
        list.insertAtFirst(74);
        list.insertAtFirst(56);
        list.insertAtLast(41);
        list.insertAtLast(46);
        System.out.println("" + list.Find(23).data);
        System.out.println("" + list.get(4).data);
        System.out.println("Values: " + list.toString());
        System.out.println("Values: " + list.toStringReverse());
        list.insert(5, 98);
        list.insert(8, 76);
        System.out.println("Values: " + list.toString());
        list.removeFirst();
        System.out.println("Values: " + list.toString());
        list.removeLast();
        System.out.println("Values: " + list.toString());
        list.remove(0);
        System.out.println("Values: " + list.toString());
        list.remove(5);
        System.out.println("Values: " + list.toString());
        System.out.println("Values: " + list.toStringReverse());
    }

    public static void linearLinkedList() {
        IntLinkedList list = new IntLinkedList();
        list.insertAtLast(201);
        list.insertAtFirst(20);
        list.insertAtFirst(23);
        list.insertAtFirst(22);
        list.insertAtFirst(74);
        list.insertAtFirst(56);
        list.insertAtLast(46);
        System.out.println("Values: " + list.toString());
        list.insert(5, 98);
        list.insert(8, 76);
        System.out.println("Values: " + list.toString());
        list.removeFirst();
        System.out.println("Values: " + list.toString());
        list.removeLast();
        System.out.println("Values: " + list.toString());
        list.remove(0);
        System.out.println("Values: " + list.toString());
        list.remove(5);
        System.out.println("Values: " + list.toString());
        System.out.println("" + list.get(4).data);
        System.out.println("" + list.Find(23).data);
    }
}

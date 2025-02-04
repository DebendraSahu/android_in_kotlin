package task_b;

public class Main {
    public static void main(String[] args) {
        IntLinkedList list = new IntLinkedList();
        list.insertAtLast(201);
        list.insertAtFirst(20);
        list.insertAtFirst(23);
        list.insertAtFirst(22);
        list.insertAtFirst(4);
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

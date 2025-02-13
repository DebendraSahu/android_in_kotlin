package task_c;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.populate(scanner);
        tree.display();
        scanner.close();
        System.out.println("Max depth of tree: " + tree.getMaxDepth());
    }
}



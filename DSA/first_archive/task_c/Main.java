package first_archive.task_c;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        BinaryTree tree = new BinaryTree();
        try (Scanner scanner = new Scanner(System.in)) {
            tree.populate(scanner);
            tree.display();
        }
        System.out.println("Max depth of tree: " + tree.getMaxDepth());
        System.out.println("Checking different roots are idetical: " + tree.areIdentical(tree.root, tree.root.left));
    }
}



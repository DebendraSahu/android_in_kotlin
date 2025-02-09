package task_c;



 import java.util.Locale;
 import java.util.Scanner;

 public class BinaryTree {
     Node root;

     public class Node {
         int value;
         Node left, right;

         public Node(int value) {
             this.value = value;
         }
     }

     public void populate(Scanner scanner) {
         System.out.println("Enter the Root Node");
         int value = scanner.nextInt();
         root = new Node(value);
         populate(scanner, root);
     }

     public void populate(Scanner scanner, Node node) {
         System.out.println("Dou you want to enter left of " + node.value);
         String input = scanner.next();
         if (input.toLowerCase(Locale.getDefault()).contains("y")) {
             System.out.println("Enter the value");
             int value = scanner.nextInt();
             node.left = new Node(value);
             populate(scanner, node.left);
         }
         System.out.println("Dou you want to enter right of " + node.value);
         input = scanner.next();
         if (input.toLowerCase(Locale.getDefault()).contains("y")) {
             System.out.println("Enter the value");
             int value = scanner.nextInt();
             node.right = new Node(value);
             populate(scanner, node.right);
         }
     }

     private void display(Node node, String indent) {
         if (node == null)
             return;
         System.out.println(indent + node.value);
         display(node.left, indent + "\t");
         display(node.right, indent + "\t");
     }

     public void displayPretty(Node node, int level, StringBuilder indentBuilder) {
         if (node == null)
             return;
         displayPretty(node.right, level + 1, indentBuilder);
         if (level != 0) {
             for (int i = 0; i < level - 1; i++) {
                 System.out.print("|\t\t");
                 indentBuilder.append("|\t\t");
             }
             System.out.println("|------>" + node.value);
             indentBuilder.append("|------>").append(node.value).append("\n");
         } else {
             System.out.println(node.value);
             indentBuilder.append(node.value).append("\n");
         }
         displayPretty(node.left, level + 1, indentBuilder);

     }

     public void display() {
         StringBuilder indentBuilder = new StringBuilder("\nAnother \n"); // "maybe: \n";
         displayPretty(root, 0, indentBuilder);
         System.out.println(indentBuilder);
     }
 }
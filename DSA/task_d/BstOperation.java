package task_d;


class BstTree {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return;
        }

        Node parentNode = root;
        Node tempNode = parentNode;

        while (tempNode != null) {
            parentNode = tempNode;
            if (tempNode.value > value) {
                tempNode = tempNode.left;
            } else if (tempNode.value < value) {
                tempNode = tempNode.right;
            } else {
                return;
            }
        }

        if (parentNode.value > value) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }
    }

    private int height(Node root) {
        if (root == null) return -1;

        // compute the height of left and right subtrees
        int lHeight = height(root.left);
        int rHeight = height(root.right);

        return Math.max(lHeight, rHeight) + 1;
    }

    public int getHeight() {
        return height(root);
    }

    public int findLargestValue() {
        if (root == null) return -1;
        Node temp = root;
        int max = root.value;
        while (temp != null) {
            max = temp.value;
            temp = temp.right;
        }
        return max;
    }

    public int findSmallestValue() {
        if (root == null) return -1;
        Node temp = root;
        int min = root.value;
        while (temp != null) {
            min = temp.value;
            temp = temp.left;
        }
        return min;
    }

    private void printNode(Node node) {
        if (node != null) {
            printNode(node.left);
            System.out.print(node.value + " ");
            printNode(node.right);
        }
    }

    public void print() {
        Node tempNode = root;
        System.out.print("Elements of tree in oder: ");
        printNode(tempNode);
        System.out.println();
    }
}

public class BstOperation {
    public static void main(String[] args) {
        BstTree tree = new BstTree();
        tree.insert(10);
        tree.insert(3);
        tree.insert(16);
        tree.insert(-1);
        tree.insert(40);
        tree.insert(22);
        tree.insert(1);
        tree.insert(19);
        tree.insert(1);
        tree.insert(70);
        tree.insert(9);
        tree.insert(10000);
        tree.print();
        System.out.println("Max in tree is " + tree.findLargestValue());
        System.out.println("Min in tree is " + tree.findSmallestValue());
        System.out.println("height of tree is " + tree.getHeight());
    }
}

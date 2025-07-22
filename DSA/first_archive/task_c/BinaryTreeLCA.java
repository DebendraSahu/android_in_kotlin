package first_archive.task_c;


public class BinaryTreeLCA {
    static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }

    // Function to find the lowest common ancestor
    public static Node findLCA(Node root, int n1, int n2) {
        // Base case: if root is null, return null
        if (root == null) {
            return null;
        }

        // If either n1 or n2 matches the root's value, return the root
        if (root.value == n1 || root.value == n2) {
            return root;
        }

        // Recur for the left and right subtrees
        Node leftLCA = findLCA(root.left, n1, n2);
        Node rightLCA = findLCA(root.right, n1, n2);

        // If both subtrees return non-null, this node is the LCA
        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        // Otherwise, return the non-null subtree
        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    public static void main(String[] args) {
        // Constructing a binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // Find LCA of nodes 4 and 5
        Node lca = findLCA(root, 4, 5);
        if (lca != null) {
            System.out.println("LCA of 4 and 5 is: " + lca.value);
        } else {
            System.out.println("LCA not found.");
        }
    }
}

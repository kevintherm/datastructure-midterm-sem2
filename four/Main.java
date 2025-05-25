public class Main {
    public static void main(String[] args) {

        /**
         * Lakukan setiap langkah dibawah ini untuk setiap poinnya.
         * a. Gambarlah diagram Binary Search Tree langkah demi langkah menggunakan
         * elemen data yang di- insert secara berurutan berikut ini (sebagai rootnya
         * adalah 10): 10, 17, 25, 4, 7, 9, 2, 15, 21, 27.
         * (bobot 10)
         * b. Buatlah sebuah program dalam bahasa Java yang mengimplementasikan Binary
         * Search Tree (BST) pada poin (a). (bobot 15)
         * 
         */

        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(10);
        bst.insert(17);
        bst.insert(25);
        bst.insert(4);
        bst.insert(7);
        bst.insert(9);
        bst.insert(2);
        bst.insert(15);
        bst.insert(21);
        bst.insert(27);

        bst.Inorder(bst.root);

    }
}

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    public Node insert(int data) {
        Node newNode = new Node(data);

        // handle root null
        if (root == null) {
            root = newNode;
            return newNode;
        }

        Node current = root;
        Node parent = null;

        // traversal
        while (current != null) {
            parent = current;

            // cek, jika data kurang dari current, traversal ke kiri. else ke kanan
            if (data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // cek penempatan data, kiri jika less dari parent
        if (data < parent.data)
            parent.left = newNode;
        // else taruh di kanan
        else
            parent.right = newNode;

        return newNode;
    }

    // traversal
    public void traversal() {
        Inorder(root);
    }

    public void Inorder(Node root) {
        if (root == null)
            return;
        else {
            Inorder(root.left);
            System.out.print(root.data + " ");
            Inorder(root.right);
        }
    }
}
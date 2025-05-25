import java.util.*;

public class Main {
    public static void main(String[] args) {

        /*
         * Terdapat sebuah deret angka sebagai berikut:
         * 53,64, 128, 113, 124, 248, 122, 134, 268, 280
         * Buat program bahasa Java untuk menginput deret bilangan diatas dengan
         * menggunakan konsep stack! Silakan menggunakan representasi Array (bobot 10)
         * dan Linked list (bobot 10).
         */

        int[] deret = { 53, 64, 128, 113, 124, 248, 122, 134, 268, 280 };

        // array sbg stack
        ArrayStack arrayStack = new ArrayStack(deret.length);

        // linked list sbg stack
        LinkedListStack linkedListStack = new LinkedListStack();

        for (int i : deret) {
            arrayStack.insert(i);
            linkedListStack.insert(i);
        }

        System.out.println("Array Stack Semula:");
        arrayStack.printStack();

        System.out.println("Linked List Stack Semula:");
        linkedListStack.printStack();

        System.out.println("\nPop 2x");
        arrayStack.pop();
        arrayStack.pop();
        linkedListStack.pop();
        linkedListStack.pop();

        System.out.println("Array Stack:");
        arrayStack.printStack();

        System.out.println("Linked List Stack:");
        linkedListStack.printStack();

        System.out.println("\nInsert 1x");
        arrayStack.insert(999);
        linkedListStack.insert(999);
        
        System.out.println("Array Stack:");
        arrayStack.printStack();

        System.out.println("Linked List Stack:");
        linkedListStack.printStack();

    }
}

class ArrayStack {
    int top;
    Integer[] container;

    // init class set top menjadi -1 utk kosong dan init array
    ArrayStack(int size) {
        top = 0;
        container = new Integer[size];
    }

    // mengambil data paling atas tanpa mengambilnya
    public void peek() {
        if (top == 0) {
            System.out.println("Stack empty");
            return;
        }

        System.out.println("Peek: " + container[top]);
        return;
    }

    // insert data paling atas
    public void insert(int data) {
        if (top == container.length) {
            System.out.println("Stack full.");
            return;
        }

        container[top] = data;
        top++;
    }

    // mengambil data paling atas
    public Integer pop() {
        if (top == 0) {
            System.out.println("Stack empty.");
            return null;
        }

        top--;
        Integer data = container[top];
        container[top] = null;
        return data;
    }

    // remove semua data pada stack
    public void popAll() {
        for (int i = top; i >= 0; i--) {
            container[i] = null;
        }

        top = 0;
    }

    public void printStack() {
        if (top == 0) {
            System.out.println("Stack empty.");
            return;
        }

        for (int i = 0; i < top; i++) {
            System.out.print(" " + container[i] + " " + (i < top - 1 ? "-> " : ""));
        }

        System.out.println(" <- Top");
    }

}

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedListStack {
    Node top;

    // insert data
    public Node insert(int data) {
        // create node lalu jadikan node sebagai top bar
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;

        return newNode;
    }

    public Node pop() {
        // cek top null
        if (top == null) {
            System.out.println("Stack empty.");
            return null;
        }

        Node data = top;
        top = data.next;
        return data;
    }

    public void popAll() {
        if (top == null) {
            System.out.println("Stack empty");
        }

        Node curr = top;
        while (curr != null) {

            if (curr.next != null) {

            }

            curr = curr.next;
        }

    }

    // lihat data tanpa ngambil
    public Node peek() {
        if (top == null) {
            return null;
        }

        return top;
    }

    // traversal utk print data
    public void printStack() {

        // store data di var utk di print scr reverse
        List<Integer> temp = new ArrayList<>();
        Node curr = top;

        while (curr != null) {
            temp.add(curr.data);
            curr = curr.next;
        }

        // print stack
        for (int i = temp.size() - 1; i >= 0; i--) {
            System.out.print(" " + temp.get(i) + " " + (i > 0 ? "-> " : ""));
        }

        System.out.println(" <- Top");
    }
}
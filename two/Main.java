public class Main {
    public static void main(String[] args) {

        /*
         * Terdapat sebuah deret angka sebagai berikut :
         * 43, 5, 9, 42, 6, 11, 41, 7, 13, 100, 12, 70, 24, 40, 36, 16, 35, 14, 30, 25
         * Buat program Bahasa Java menggunakan konsep Single Linked List. Lakukan
         * setiap langkah dibawah ini untuk setiap poinnya.
         * 
         * a. Insert semua angka tersebut ke Single Linked List dan tampilkan! (bobot 5)
         * b. Lakukan proses insert angka 59 setelah angka 100! (bobot 5)
         * c. Lakukan proses delete angka 40! (bobot 5)
         * 
         */

        int[] deret = { 43, 5, 9, 42, 6, 11, 41, 7, 13, 100, 12, 70, 24, 40, 36, 16, 35, 14, 30, 25 };

        SingleLinkedList list = new SingleLinkedList();

        // a. Insert semua angka tersebut ke Single Linked List dan tampilkan!

        // reverse loop, agar menjadikan 43 sebagai head
        for (int i = deret.length - 1; i >= 0; i--) {
            list.insert(deret[i]);
        }

        list.traversal();

        // b. Lakukan proses insert angka 59 setelah angka 100!
        System.out.println("\nInsert angka 59 setelah angka 100");
        list.insertAfter(100, 59);

        list.traversal();

        // c. Lakukan proses delete angka 40!
        System.out.println("\nDelete angka 40");
        list.delete(40);

        list.traversal();
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

class SingleLinkedList {
    Node head;

    // insert data 
    public Node insert(int data) {
        // handle jika head null
        if (head == null) {
            head = new Node(data);
            return head;
        }

        // create node lalu jadikan node sebagai head baru
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;

        return newNode;
    }

    public Node insertAfter(int after, int data) {
        // handle jika head null
        if (head == null) {
            head = new Node(data);
            return head;
        }
        
        Node curr = head;
        while (curr != null) {

            // cek apakah data sama dengan yang dicari
            if (curr.data == after) {
                // insert
                Node newData = new Node(data);
                newData.next = curr.next;
                curr.next = newData;
                return newData;
            }

            curr = curr.next;
        }

        return null;
    }

    public Node delete(int find) {
        // cek head null
        if (head == null) {
            return null;
        }

        // cek apakah data yg dicari berada pada head
        if (head.data == find) {
            Node found = head;
            head = head.next;
            return found;
        }

        Node curr = head;
        while (curr != null) {
            // delete node, dan atur pointer next
            if (curr.next.data == find) {
                Node found = curr.next;
                curr.next = curr.next.next;
                return found;
            }

            curr = curr.next;
        }

        return null;
    }

    // traversal utk print data
    public void traversal() {
        Node curr = head;

        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }

        System.out.println();
    }
}
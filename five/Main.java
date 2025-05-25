import java.util.LinkedList;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        /**
         * Lakukan setiap langkah dibawah ini untuk setiap poinnya.
         * a. Gambarlah Hash tabel berukuran 10 dengan urutan insert: Anting, Baju,
         * Celana, Gelas, Gunting, Handphone, Buku, Garpu, Jarum, Benang. Lakukan Linier
         * Probing jika terjadi collision. (bobot 10)
         * b. Buatlah sebuah program dalam bahasa Java yang mengimplementasikan Linier
         * Probing pada poin (a). (bobot 15)
         * 
         */

        // inisiasi hash table dgn size 10
        HashTable hashTable = new HashTable(10);

        // insert values
        System.out.println("Hash table dengan linear probing:");
        hashTable.insert("Anting");
        hashTable.insert("Baju");
        hashTable.insert("Celana");
        hashTable.insert("Gelas");
        hashTable.insert("Gunting");
        hashTable.insert("Handphone");
        hashTable.insert("Buku");
        hashTable.insert("Garpu");
        hashTable.insert("Jarum");
        hashTable.insert("Benang");

        hashTable.display();

        // check key
        System.out.println("\nMencari index dengan key Baju:");
        hashTable.find("Anting");
        

    }
}

// implementasi class HashTable
class HashTable {
    private ArrayList<LinkedList<String>> table;
    private int size;

    HashTable(int size) {
        this.size = size;
        table = new ArrayList<LinkedList<String>>();
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<>());
        }
    }

    // function utk insert ke table
    public void insert(String value) {
        int key = hash(value);
        if (key >= size) {
            System.out.println("Table penuh.");
            return;
        }
        
        table.get(key).add(value);
    }

    // check apakah elemen ada pada hashtable O(1)
    public void find(String find) {
        int key = hash(find, true);

        if (key > size) {
            System.out.println("Elemen tidak ditemukan.");
            return;
        }

        // menampilkan list pada hash table
        System.out.println("Hash Index: " + key);
        System.out.println(table.get(key));
    }

    // print table
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + table.get(i));
        }
    }

    // hash function dengan linear probing
    private int hash(String str) {
        return hash(str, false); // default parameter
    }

    private int hash(String str, boolean ignoreCollision) {
        // algoritma hashing: str length % table size
        int hashVal = str.length();
        int key = hashVal % size;

        if (ignoreCollision) return key;

        // handle collision
        int add = 1;
        while (table.get(key).size() > 0) {
            key = (hashVal + add) % size;
            add++;
        }

        return key;
    }

}
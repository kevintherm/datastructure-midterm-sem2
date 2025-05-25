import java.util.*;
import java.text.*;

public class ManajemenProduk {

    private static Scanner scanner = new Scanner(System.in);
    private static LinkedList<Product> products = new LinkedList<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n==== MANAJEMEN PRODUK ====");
            System.out.println("===== PILIH OPERASI =====");
            System.out.println("1. Tampilkan semua produk");
            System.out.println("2. Tambah produk");
            System.out.println("3. Update produk");
            System.out.println("4. Hapus produk");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            System.out.println();

            // switch controller utk mengarahkan menu
            switch (choice) {
                case 1 -> listProducts();
                case 2 -> addProduct();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 0 -> System.out.println("Keluar dari program.");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 0);

        scanner.close();

    }

    private static void listProducts() {
        System.out.println("Menampilkan semua produk.");

        if (products.size() < 1) {
            System.out.println("Produk kosong.");
            return;
        }

        for (Product product : products) {
            product.displayInfo();
        }
    }

    private static void addProduct() {
        System.out.println("Tambah produk.");

        System.out.print("Nama produk: ");
        String name = scanner.nextLine();

        System.out.print("Brand produk: ");
        String brand = scanner.nextLine();

        // cek duplikat
        int duplicate = -1;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getName().equals(name) && p.getBrand().equals(brand))
                duplicate = i;
        }

        // handle duplikat
        String confirmDuplicate;
        if (duplicate != -1) {
            System.out.print("Produk sudah ada dalam daftar. Tambahkan jumlah? (y/n): ");
            confirmDuplicate = scanner.nextLine();
            if (confirmDuplicate.toLowerCase().equals("y")) {
                Product p = products.get(duplicate);
                p.setStock(p.getStock() + 1);
                System.out.println("Produk berhasil ditambahkan!");
                return;
            } else {
                System.out.println("Aksi dibatalkan.");
                return;
            }
        }

        // handle input price
        int price = 0;
        do {
            System.out.print("Harga produk: ");
            String priceInput = scanner.nextLine();
            price = Integer.parseInt(priceInput);
            if (priceInput.isEmpty() || price < 1) {
                price = 0;
                System.out.println("Harga tidak boleh kosong.");
                return;
            }
        } while (price == 0);

        // handle input tipe
        String type;
        do {
            System.out.print("Tipe produk (elektronik/sembako): ");
            type = scanner.nextLine();
            type = type.toLowerCase();

            if (!type.equals("elektronik") && !type.equals("sembako")) {
                type = null;
                System.out.println("Tipe produk tidak valid. (elektronik/sembako)");
            }
        } while (type == null);

        // instance accept parent
        Product newProduct;

        // elektronik
        if (type.equals("elektronik")) {

            System.out.print("Garansi produk (e.g 1 tahun, 6 bulan): ");
            String warranty = scanner.nextLine();

            newProduct = new ElectronicProduct(name, brand, price, warranty);

            products.add(newProduct);

            System.out.println("Produk berhasil ditambahkan!");
            return;
        }

        // sembako
        System.out.print("Kadaluwarsa produk (e.g 21-06-2025): ");
        String expiry = scanner.nextLine();

        newProduct = new GroceryProduct(name, brand, price, expiry);

        products.add(newProduct);

        System.out.println("Produk berhasil ditambahkan!");
    }

    private static void updateProduct() {
        System.out.println("Update barang.");

        System.out.print("Nama barang: ");
        String name = scanner.nextLine();

        System.out.print("Brand barang: ");
        String brand = scanner.nextLine();

        Product find = null;
        for (Product p : products) {
            if (p.getName().equals(name) && p.getBrand().equals(brand))
                find = p;
        }

        System.out.println();

        // handle tidak ditemukan
        if (find == null) {
            System.out.println("Produk tidak ditemukan dalam daftar.");
            return;
        }

        System.out.println("Produk ditemukan!");
        find.displayInfo();

        System.out.println("Kosongkan isian jika tidak ingin mengubahnya.");

        System.out.print("Nama: ");
        name = scanner.nextLine();

        System.out.print("Brand: ");
        brand = scanner.nextLine();

        System.out.print("Harga produk: ");
        String priceInput = scanner.nextLine();

        System.out.print("Stok produk: ");
        String stockInput = scanner.nextLine();

        // handle update logic, jika kosong gunakan data awal
        int price = 0;
        int stock = 1;

        if (name.isEmpty())
            name = find.getName();
        if (brand.isEmpty())
            brand = find.getBrand();

        if (priceInput.isEmpty())
            price = find.getPrice();
        else {
            price = Integer.parseInt(priceInput);
        }

        if (stockInput.isEmpty())
            stock = find.getStock();
        else
            stock = Integer.parseInt(stockInput);

        // elektronik
        if (find.getType().equalsIgnoreCase("elektronik")) {

            ElectronicProduct product = (ElectronicProduct) find;

            System.out.print("Garansi produk (e.g 1 tahun, 6 bulan): ");
            String warranty = scanner.nextLine();

            if (warranty.isEmpty())
                warranty = product.getWarranty();

            product.setName(name);
            product.setBrand(brand);
            product.setPrice(price);
            product.setStock(stock);
            product.setWarranty(warranty);

            System.out.println("Produk berhasil di-update!");
            return;
        }

        // sembako
        GroceryProduct product = (GroceryProduct) find;

        System.out.print("Kadaluwarsa produk (e.g 21-06-2025): ");
        String expiry = scanner.nextLine();

        if (expiry.isEmpty())
                expiry = product.getExpiry();

        product.setName(name);
        product.setBrand(brand);
        product.setPrice(price);
        product.setStock(stock);
        product.setExpiry(expiry);

        System.out.println("Produk berhasil di-update!");
    }

    private static void deleteProduct() {
        System.out.println("Hapus produk.");

        System.out.print("Nama produk: ");
        String name = scanner.nextLine();

        System.out.print("Brand produk: ");
        String brand = scanner.nextLine();

        int find = -1;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getName().equalsIgnoreCase(name) && p.getBrand().equalsIgnoreCase(brand)) find = i;
        }

        System.out.println();

        if (find == -1) {
            System.out.println("Produk tidak ditemukan.");
            return;
        }

        products.get(find).displayInfo();

        System.out.print("Hapus produk? (y/n): ");
        String choice = scanner.nextLine();

        System.out.println();

        if (choice.equals("y")) {
            products.remove(find);
            System.out.println("Produk berhasil dihapus!");
            return;
        }

        System.out.println("Aksi dibatalkan.");

    }

}

// parent class product
abstract class Product {
    private static NumberFormat money = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    private String name;
    private String brand;
    private int price;
    private int stock;

    Product(String name, String brand, int price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public String getFPrice() {
        return money.format(price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void displayInfo() {
        System.out.println(
                "Produk: " + getName() + " " + getBrand() + " | Harga: " + getFPrice() + " | Stok: " + getStock());
    }

    // abstraksi method untuk di override di child class
    abstract public String getType();

}

// child class untuk tipe sembako dan produk elektronik
class GroceryProduct extends Product {
    private String expiry;

    GroceryProduct(String name, String brand, int price, String expiry) {
        super(name, brand, price);
        this.expiry = expiry;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    // polymorphism implementasi method dgn berbagai cara
    @Override
    public void displayInfo() {
        System.out.println("Produk Sembako: " + getName() + " " + getBrand() + " | Harga: " + getFPrice()
                + " | Kadaluwarsa: " + getExpiry() + " | Stok: " + getStock());
    }

    public String getType() {
        return "Sembako";
    }
}

class ElectronicProduct extends Product {
    private String warranty;

    ElectronicProduct(String name, String brand, int price, String warranty) {
        super(name, brand, price);
        this.warranty = warranty;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    @Override
    public void displayInfo() {
        System.out.println("Produk Elektronik: " + getName() + " " + getBrand() + " | Harga: " + getFPrice()
                + " | Garansi: " + getWarranty() + " | Stok: " + getStock());
    }

    public String getType() {
        return "Elektronik";
    }
}
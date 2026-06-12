import java.util.Scanner;
// Product Class
class Product {
    String productName;
    int quantity;
    int threshold;

    // Parameterized Constructor
    Product(String productName, int quantity, int threshold) {
        this.productName = productName;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    // Method to display product details
    void displayProduct() {
        System.out.println("Product Name: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Threshold: " + threshold);
    }
}


// InventoryManager Class
class InventoryManager {
    Product[] products;
    int size;

    // Constructor
    InventoryManager(int size) {
        this.size = size;
        products = new Product[size];
    }

    // Method to add product
    void addProduct(int index, Product p) {
        products[index] = p;
    }

    // Method to check low stock
    void checkLowStock() {
        System.out.println("\nChecking Inventory...\n");

        for (int i = 0; i < size; i++) {
            if (products[i].quantity < products[i].threshold) {
                System.out.println(products[i].productName + " is LOW on stock!");
            } else {
                System.out.println(products[i].productName + " is Stock OK.");
            }
        }
    }

    // Method to display all products
    void displayAllProducts() {
        System.out.println("\nAll Product Details:\n");

        for (int i = 0; i < size; i++) {
            products[i].displayProduct();
            System.out.println("----------------------");
        }
    }
}


// Main Class

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        InventoryManager manager = new InventoryManager(n);

        // Taking input
        for (int i = 0; i < n; i++) {
            sc.nextLine(); // buffer clear

            System.out.println("\nEnter details for Product " + (i + 1));

            System.out.print("Enter Product Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            System.out.print("Enter Threshold: ");
            int th = sc.nextInt();

            Product p = new Product(name, qty, th);
            manager.addProduct(i, p);
        }

        // Display all products
        manager.displayAllProducts();

        // Check stock
        manager.checkLowStock();

        sc.close();
    }
}
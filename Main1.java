import java.util.Scanner;
// Interface (Abstraction)
interface StockOperations {
    void checkLowStock();
}
// Base Class (Inheritance)
class Item {
    protected String productName;

    Item(String productName) {
        this.productName = productName;
    }
}
// Encapsulation + Data Hiding
class Product extends Item {

    private int quantity;
    private int threshold;

    // Constructor
    Product(String productName, int quantity, int threshold) {
        super(productName); // inheritance (Unit 4)
        this.quantity = quantity;
        this.threshold = threshold;
    }

    // Getters (Encapsulation)
    public int getQuantity() {
        return quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public String getProductName() {
        return productName;
    }

    // Setter with validation (Defensive coding - Unit 5)
    public void setQuantity(int quantity) throws Exception {
        if (quantity < 0) {
            throw new Exception("Quantity cannot be negative!");
        }
        this.quantity = quantity;
    }

    // Display Method
    void displayProduct() {
        System.out.println("Product Name: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Threshold: " + threshold);
    }
}
// Inheritance + Interface + Polymorphism
class InventoryManager implements StockOperations {

    Product[] products;
    int size;

    InventoryManager(int size) {
        this.size = size;
        products = new Product[size];
    }

    void addProduct(int index, Product p) {
        products[index] = p;
    }

    // Method Overriding (Polymorphism)
    @Override
    public void checkLowStock() {
        System.out.println("\nChecking Inventory...\n");

        for (int i = 0; i < size; i++) {
            if (products[i].getQuantity() < products[i].getThreshold()) {
                System.out.println(products[i].getProductName() + " is LOW on stock!");
            } else {
                System.out.println(products[i].getProductName() + " is Stock OK.");
            }
        }
    }

    void displayAllProducts() {
        System.out.println("\nAll Product Details:\n");

        for (int i = 0; i < size; i++) {
            products[i].displayProduct();
            System.out.println("----------------------");
        }
    }
}


// ================= MAIN CLASS =================
public class Main1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter number of products: ");
            int n = sc.nextInt();

            InventoryManager manager = new InventoryManager(n);

            for (int i = 0; i < n; i++) {
                sc.nextLine();

                System.out.println("\nEnter details for Product " + (i + 1));

                System.out.print("Enter Product Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Quantity: ");
                int qty = sc.nextInt();

                System.out.print("Enter Threshold: ");
                int th = sc.nextInt();

                Product p = new Product(name, qty, th);

                // Exception handling (Unit 5)
                try {
                    p.setQuantity(qty);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }

                manager.addProduct(i, p);
            }

            manager.displayAllProducts();
            manager.checkLowStock();

        } catch (Exception e) {
            System.out.println("Invalid Input!");
        } finally {
            sc.close();
            System.out.println("\nProgram Ended Safely.");
        }
    }
}
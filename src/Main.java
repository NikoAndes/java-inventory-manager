import service.InventoryService;
import model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Console interface for the Java Inventory Manager.
 * Entry point for the application.
 */
public class Main {
    private static final String DATA_FILE = "data/inventory.csv";
    private static InventoryService service;
    private static Scanner scanner;

    public static void main(String[] args) {
        service = new InventoryService(DATA_FILE);
        scanner = new Scanner(System.in);
        System.out.println("==================================");
        System.out.println("    Java Inventory Manager v1.0   ");
        System.out.println("==================================");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> listAllProducts();
                case "2" -> addProduct();
                case "3" -> searchProduct();
                case "4" -> removeProduct();
                case "5" -> showLowStock();
                case "6" -> showStats();
                case "0" -> running = false;
                default  -> System.out.println("Invalid option. Try again.");
            }
        }
        System.out.println("Goodbye!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. List all products");
        System.out.println("2. Add product");
        System.out.println("3. Search by name");
        System.out.println("4. Remove product");
        System.out.println("5. Low-stock alerts");
        System.out.println("6. Inventory stats");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    private static void listAllProducts() {
        List<Product> all = service.getAllProducts();
        if (all.isEmpty()) { System.out.println("No products found."); return; }
        all.forEach(System.out::println);
    }

    private static void addProduct() {
        System.out.print("Name: ");     String name = scanner.nextLine();
        System.out.print("Category: "); String cat  = scanner.nextLine();
        System.out.print("Price: ");    double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantity: "); int qty       = Integer.parseInt(scanner.nextLine());
        Product p = service.addProduct(name, cat, price, qty);
        System.out.println("Added: " + p);
    }

    private static void searchProduct() {
        System.out.print("Keyword: ");
        String kw = scanner.nextLine();
        List<Product> results = service.searchByName(kw);
        if (results.isEmpty()) System.out.println("No matches.");
        else results.forEach(System.out::println);
    }

    private static void removeProduct() {
        System.out.print("Product ID to remove: ");
        String id = scanner.nextLine();
        boolean ok = service.removeProduct(id);
        System.out.println(ok ? "Removed successfully." : "Product not found.");
    }

    private static void showLowStock() {
        List<Product> low = service.getLowStockProducts();
        if (low.isEmpty()) System.out.println("All products are sufficiently stocked.");
        else { System.out.println("LOW STOCK ALERT:"); low.forEach(System.out::println); }
    }

    private static void showStats() {
        System.out.printf("Total products : %d%n", service.getAllProducts().size());
        System.out.printf("Inventory value: $%.2f%n", service.getTotalInventoryValue());
        System.out.printf("Low-stock items: %d%n", service.getLowStockProducts().size());
    }
}

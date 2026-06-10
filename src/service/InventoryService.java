package service;

import model.Product;
import util.CsvHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Core business logic for inventory management.
 */
public class InventoryService {
    private List<Product> products;
    private final String dataFile;
    private int lowStockThreshold;
    private int nextId;

    public InventoryService(String dataFile) {
        this.dataFile = dataFile;
        this.lowStockThreshold = 5;
        this.products = CsvHandler.load(dataFile);
        this.nextId = computeNextId();
    }

    private int computeNextId() {
        return products.stream()
                .mapToInt(p -> Integer.parseInt(p.getId().replace("P", "")))
                .max().orElse(0) + 1;
    }

    public Product addProduct(String name, String category, double price, int quantity) {
        String id = String.format("P%03d", nextId++);
        Product p = new Product(id, name, category, price, quantity);
        products.add(p);
        save();
        return p;
    }

    public boolean removeProduct(String id) {
        boolean removed = products.removeIf(p -> p.getId().equalsIgnoreCase(id));
        if (removed) save();
        return removed;
    }

    public Optional<Product> findById(String id) {
        return products.stream().filter(p -> p.getId().equalsIgnoreCase(id)).findFirst();
    }

    public List<Product> searchByName(String keyword) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Product> filterByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getLowStockProducts() {
        return products.stream()
                .filter(p -> p.isLowStock(lowStockThreshold))
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public void setLowStockThreshold(int threshold) {
        this.lowStockThreshold = threshold;
    }

    public double getTotalInventoryValue() {
        return products.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum();
    }

    private void save() {
        CsvHandler.save(dataFile, products);
    }
}

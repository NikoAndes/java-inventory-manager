package model;

/**
 * Represents a product in the inventory.
 */
public class Product {
    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;

    public Product(String id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getId()       { return id; }
    public String getName()     { return name; }
    public String getCategory() { return category; }
    public double getPrice()    { return price; }
    public int getQuantity()    { return quantity; }

    // Setters
    public void setName(String name)         { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price)       { this.price = price; }
    public void setQuantity(int quantity)    { this.quantity = quantity; }

    public boolean isLowStock(int threshold) {
        return quantity <= threshold;
    }

    public String toCsv() {
        return id + "," + name + "," + category + "," + price + "," + quantity;
    }

    public static Product fromCsv(String line) {
        String[] parts = line.split(",");
        return new Product(parts[0], parts[1], parts[2],
                Double.parseDouble(parts[3]), Integer.parseInt(parts[4]));
    }

    @Override
    public String toString() {
        return String.format("[%s] %-20s | Category: %-12s | Price: $%7.2f | Stock: %d",
                id, name, category, price, quantity);
    }
}

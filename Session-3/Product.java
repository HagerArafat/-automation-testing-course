
public class Product {
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product(String name, double price, int quantity, String category) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name can't be empty");
        }

        if (price < 0 || !Double.isFinite(price)) {
            throw new IllegalArgumentException("invalid price");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity can't be negative");
        }

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category can't be empty");
        }

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getCategory() {
        return category;
    }
    public void setPrice(double newPrice) {

        if (newPrice < 0 || !Double.isFinite(newPrice)) {
            throw new IllegalArgumentException("invalid price");
        }

        this.price = newPrice;
    }

    public void setQuantity(int newQuantity) {

        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity can't be negative");
        }
        this.quantity = newQuantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    public String toString() {
        return "Product{" +
                "name:'" + name + '\'' +
                ", price:" + price +
                ", quantity:" + quantity +
                ", category:'" + category + '\'' +
                '}';
    }
    private static void expectInvalid(String testName, Runnable action) {
        try {
            action.run();
            System.out.println("FAIL: " + testName + " - No exception throw");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: " + testName + " - " + e.getMessage());
        }
    }
    public static void main(String[] args) {

        Product product = new Product(
                "Sauce Labs Backpack",
                29.99,
                2,
                "Accessories"
        );

        System.out.println("=== Positive Tests ===");

        System.out.println(product);

        System.out.println("Name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Quantity: " + product.getQuantity());
        System.out.println("Category: " + product.getCategory());

        System.out.println("Total Price: " + product.getTotal());

        product.setPrice(39.99);
        product.setQuantity(3);

        System.out.println("After Update: " + product);
        System.out.println("Updated Total: " + product.getTotal());

        Product freeProduct = new Product("Free Item", 0, 0, "Offers");

        System.out.println("Zero Values Product: " + freeProduct);
        System.out.println("Zero Total: " + freeProduct.getTotal());

        product.setPrice(0);
        product.setQuantity(0);

        System.out.println("Zero Values via Setters: " + product);

        System.out.println("\n=== Negative Tests ===");

        expectInvalid("Null Name",
                () -> new Product(null, 20, 2, "Bags"));

        expectInvalid("Empty Name",
                () -> new Product("", 20, 2, "Bags"));

        expectInvalid("Blank Name",
                () -> new Product("   ", 20, 2, "Bags"));

        expectInvalid("Negative Price",
                () -> new Product("Backpack", -10, 2, "Bags"));

        expectInvalid("NaN Price",
                () -> new Product("Backpack", Double.NaN, 2, "Bags"));

        expectInvalid("Infinite Price",
                () -> new Product("Backpack", Double.POSITIVE_INFINITY, 2, "Bags"));

        expectInvalid("Negative Quantity",
                () -> new Product("Backpack", 20, -1, "Bags"));

        expectInvalid("Null Category",
                () -> new Product("Backpack", 20, 2, null));

        expectInvalid("Empty Category",
                () -> new Product("Backpack", 20, 2, ""));

        expectInvalid("Blank Category",
                () -> new Product("Backpack", 20, 2, "   "));

        expectInvalid("Negative Price Setter",
                () -> product.setPrice(-50));

        expectInvalid("NaN Price Setter",
                () -> product.setPrice(Double.NaN));

        expectInvalid("Infinite Price Setter",
                () -> product.setPrice(Double.POSITIVE_INFINITY));

        expectInvalid("Negative Quantity Setter",
                () -> product.setQuantity(-5));

        System.out.println("Final Product State: " + product);

        System.out.println("\nAll test scenarios executed.");
    }
}

public class ProductReceipt {
    public static void main(String[] args) {
        String ProductName ="Phone";
        double price = 15000.50;
        int quantity = 2;
        boolean inStock = true;
        double total_price = quantity * price;
        System.out.println("===== PRODUCT RECEIPT =====");
        System.out.println("Product Name: " + ProductName);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("In Stock: " + inStock);
        System.out.println("Product Name: " + ProductName);
        System.out.println("Total Price: " + total_price);

    }

}
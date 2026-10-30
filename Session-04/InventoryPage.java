
public class InventoryPage extends BasePage {

    private boolean loaded = false;

    public void openPage() {
        loaded = true;
        System.out.println("Inventory Page Opened");
    }

    public void showProducts() {
        System.out.println("Products:");
        System.out.println("Backpack");
        System.out.println("Bike Light");
        System.out.println("T-Shirt");
    }

    public void openCart() {
        click("Cart Button");
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }
}

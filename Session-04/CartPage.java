
public class CartPage extends BasePage {

    private boolean loaded = false;

    public void openPage() {
        loaded = true;
        System.out.println("Cart Page Opened");
    }

    public void showCart() {
        System.out.println("Your Shopping Cart");
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }
}

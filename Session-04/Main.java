
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        LoginPage loginPage = new LoginPage();
        InventoryPage inventoryPage = new InventoryPage();
        CartPage cartPage = new CartPage();

        loginPage.openPage();
        loginPage.login();

        inventoryPage.openPage();
        inventoryPage.showProducts();
        inventoryPage.openCart();

        cartPage.openPage();
        cartPage.showCart();

        List<BasePage> pages = new ArrayList<>();

        pages.add(loginPage);
        pages.add(inventoryPage);
        pages.add(cartPage);

        for (BasePage page : pages) {
            System.out.println(
                    page.getClass().getSimpleName()
                            + " Loaded: " + page.isLoaded()
            );
        }
    }
}

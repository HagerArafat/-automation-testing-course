
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            LoginPage loginPage = new LoginPage(driver);
            InventoryPage inventoryPage = new InventoryPage(driver);
            CartPage cartPage = new CartPage(driver);

            List<BasePage> pages = Arrays.asList(
                    loginPage,
                    inventoryPage,
                    cartPage
            );

            for (BasePage page : pages) {
                System.out.println(
                        page.getClass().getSimpleName()
                                + " Loaded: "
                                + page.isLoaded()
                );
            }

            Thread.sleep(10000);

        } finally {
            driver.quit();
        }
    }
}


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By cartList = By.className("cart_list");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return !driver.findElements(cartList).isEmpty();
    }
}

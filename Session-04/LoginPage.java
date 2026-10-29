
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("saucedemo.com")
                && !driver.findElements(loginButton).isEmpty();
    }
}

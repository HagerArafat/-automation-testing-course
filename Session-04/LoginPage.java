
public class LoginPage extends BasePage {

    private boolean loaded = false;

    public void openPage() {
        loaded = true;
        System.out.println("Login Page Opened");
    }

    public void login() {
        type("Username", "standard_user");
        type("Password", "secret_sauce");
        click("Login Button");
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }
}

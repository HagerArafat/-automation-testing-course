
public abstract class BasePage {

    protected void click(String element) {
        System.out.println("Clicking on: " + element);
    }

    protected void type(String element, String text) {
        System.out.println("Typing " + text + " into " + element);
    }

    protected String getText(String element) {
        return "Text from: " + element;
    }

    public abstract boolean isLoaded();
}

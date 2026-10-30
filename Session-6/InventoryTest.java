
package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InventoryTest {

    WebDriver driver;
    WebDriverWait wait;

    By productNameLocator =
            By.cssSelector("[data-test='inventory-item-name']");

    By productPriceLocator =
            By.cssSelector("[data-test='inventory-item-price']");

    By secondProductButton =
            By.id("add-to-cart-sauce-labs-bike-light");

    By cartBadgeLocator =
            By.cssSelector("[data-test='shopping-cart-badge']");

    By cartIconLocator =
            By.cssSelector("[data-test='shopping-cart-link']");

    By cartItemsLocator =
            By.cssSelector(".cart_item");



    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // لوجن هنا ع تبقي قبل كل تيست كيس
        driver.get("https://www.saucedemo.com/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("user-name")
        )).sendKeys("standard_user");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("password")
        )).sendKeys("secret_sauce");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("login-button")
        )).click();

        wait.until(ExpectedConditions.urlContains("inventory"));

        System.out.println("Login Successful");
    }


    // ======================================
    // TC1 produvt name
    // ======================================

    @Test
    public void verifyProductNames() {

        wait.until(
                ExpectedConditions.numberOfElementsToBe(
                        productNameLocator, 6
                )
        );

        List<WebElement> nameElements =
                driver.findElements(productNameLocator);

        List<String> productNames = new ArrayList<>();

        for (WebElement element : nameElements) {
            productNames.add(element.getText());
        }

        System.out.println("Product Names: " + productNames);

        Assert.assertEquals(
                productNames.size(),
                6,
                "Product names count should be 6"
        );

        Assert.assertTrue(
                productNames.contains("Sauce Labs Bike Light"),
                "Bike Light should be in the products list"
        );

        System.out.println("TC1 - Product Names Passed");
    }


    // ======================================
    // TC2 product price
    // ======================================

    @Test
    public void verifyProductPrices() {

        wait.until(
                ExpectedConditions.numberOfElementsToBe(
                        productPriceLocator, 6
                )
        );

        List<WebElement> priceElements =
                driver.findElements(productPriceLocator);

        List<String> productPrices = new ArrayList<>();

        for (WebElement element : priceElements) {
            productPrices.add(element.getText());
        }

        System.out.println("Product Prices: " + productPrices);

        Assert.assertEquals(
                productPrices.size(),
                6,
                "Product prices count should be 6"
        );

        for (String price : productPrices) {
            Assert.assertTrue(
                    price.startsWith("$"),
                    "Product price should start with $"
            );
        }

        System.out.println("TC2 - Product Prices Passed");
    }


    // ======================================
    // TC3 add item 2 to cart
    // ======================================

    @Test
    public void verifyAddSecondProductToCart() {

        addSecondProductToCart();

        WebElement cartBadge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        cartBadgeLocator
                )
        );

        Assert.assertEquals(
                cartBadge.getText(),
                "1",
                "Cart badge should show 1"
        );

        System.out.println("TC3 - Add to Cart Passed");
    }


    // ======================================
    // TC4 cart item
    // ======================================

    @Test
    public void verifyCartItems() {

        addSecondProductToCart();

        wait.until(ExpectedConditions.elementToBeClickable(
                cartIconLocator
        )).click();

        wait.until(ExpectedConditions.urlContains("cart.html"));

        wait.until(ExpectedConditions.numberOfElementsToBe(
                cartItemsLocator, 1
        ));

        List<WebElement> cartItems =
                driver.findElements(cartItemsLocator);

        Assert.assertEquals(
                cartItems.size(),
                1,
                "Cart should contain one item"
        );

        WebElement cartProductName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productNameLocator
                )
        );

        Assert.assertEquals(
                cartProductName.getText(),
                "Sauce Labs Bike Light",
                "Wrong product in cart"
        );

        System.out.println("TC4 - Cart Items Passed");
    }


    // ======================================
    // TC5 checkout
    // ======================================

    @Test
    public void verifyCheckoutSuccessfully() {

        addSecondProductToCart();

        // 1.pen cart
        wait.until(ExpectedConditions.elementToBeClickable(
                cartIconLocator
        )).click();

        wait.until(ExpectedConditions.urlContains("cart.html"));

        // 2.click checkout
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("checkout")
        )).click();

        wait.until(
                ExpectedConditions.urlContains("checkout-step-one")
        );

        // 3.add information
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("first-name")
        )).sendKeys("Test");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("last-name")
        )).sendKeys("User");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("postal-code")
        )).sendKeys("12345");

        // 4. click continue
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("continue")
        )).click();

        wait.until(
                ExpectedConditions.urlContains("checkout-step-two")
        );

        //  product in checkout
        WebElement overviewProduct = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productNameLocator
                )
        );

        Assert.assertEquals(
                overviewProduct.getText(),
                "Sauce Labs Bike Light"
        );

        // 5.finish
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("finish")
        )).click();

        wait.until(
                ExpectedConditions.urlContains("checkout-complete")
        );

        //  success message
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test='complete-header']")
                )
        );

        Assert.assertEquals(
                successMessage.getText(),
                "Thank you for your order!",
                "Checkout confirmation is incorrect"
        );

        System.out.println("TC5 - Checkout Passed");
    }




    public void addSecondProductToCart() {

        wait.until(ExpectedConditions.elementToBeClickable(
                secondProductButton
        )).click();
    }




    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}

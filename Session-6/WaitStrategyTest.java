
package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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

public class WaitStrategyTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        driver.get("https://www.saucedemo.com/");
    }


    // ========================================
    // TC1 wrong locator
    // ========================================

    @Test
    public void verifyWrongLocatorException() {

        By wrongLocator = By.id("wrong-username-id");

        NoSuchElementException exception = Assert.expectThrows(
                NoSuchElementException.class,
                () -> driver.findElement(wrongLocator)
        );

        System.out.println(
                "Expected exception: " +
                        exception.getClass().getSimpleName()
        );

        System.out.println("TC1 Passed");
    }


    // ========================================
    // TC2 fix
    // ========================================

    @Test
    public void verifyCorrectLocator() {

        By correctLocator = By.id("user-name");

        WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        correctLocator
                )
        );

        Assert.assertTrue(
                username.isDisplayed(),
                "Username field should be visible"
        );

        username.sendKeys("standard_user");

        System.out.println("TC2 Correct Locator Passed");
    }


    // ========================================
    // TC3 element + locator
    // ========================================

    @Test
    public void verifyDelayedElementWithExplicitWait() {

        JavascriptExecutor js = (JavascriptExecutor) driver;


        js.executeScript(
                "setTimeout(function() {" +
                        "  var element = document.createElement('div');" +
                        "  element.id = 'delayed-element';" +
                        "  element.textContent = 'Element Loaded Successfully';" +
                        "  document.body.appendChild(element);" +
                        "}, 3000);"
        );

        By delayedLocator = By.id("delayed-element");

        Assert.assertTrue(
                driver.findElements(delayedLocator).isEmpty(),
                "Element should not exist immediately"
        );

        WebElement delayedElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        delayedLocator
                )
        );

        Assert.assertEquals(
                delayedElement.getText(),
                "Element Loaded Successfully",
                "Delayed element text is incorrect"
        );

        System.out.println("TC3 Explicit Wait Passed");
    }


    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}

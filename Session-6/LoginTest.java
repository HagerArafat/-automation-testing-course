
package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // TC 1: Valid Login
    @Test
    public void validLoginTest() {

        driver.get("https://www.saucedemo.com/");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("user-name")
                )
        ).sendKeys("standard_user");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("password")
                )
        ).sendKeys("secret_sauce");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("login-button")
                )
        ).click();

        wait.until(
                ExpectedConditions.urlContains("inventory")
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory"),
                "Login failed: Inventory page was not opened"
        );

        System.out.println("Valid Login Test Passed");
    }

    // TC 2: Invalid Password
    @Test
    public void invalidPasswordTest() {

        driver.get("https://www.saucedemo.com/");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("user-name")
                )
        ).sendKeys("standard_user");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("password")
                )
        ).sendKeys("wrong123");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("login-button")
                )
        ).click();

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test='error']")
                )
        );

        String actualError = errorMessage.getText();

        String expectedError =
                "Epic sadface: Username and password do not match any user in this service";

        Assert.assertEquals(actualError, expectedError);

        System.out.println("Invalid Password Test Passed");
    }

    // TC3: Empty Username
    @Test
    public void emptyUsernameTest() {

        driver.get("https://www.saucedemo.com/");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("user-name")
                )
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("password")
                )
        ).sendKeys("secret_sauce");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("login-button")
                )
        ).click();

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test='error']")
                )
        );

        String actualError = errorMessage.getText();

        String expectedError = "Epic sadface: Username is required";

        Assert.assertEquals(
                actualError,
                expectedError,
                "Incorrect validation message for empty username"
        );

        System.out.println("Empty Username Test Passed");
    }




    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}

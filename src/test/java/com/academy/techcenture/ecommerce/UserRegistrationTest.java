package com.academy.techcenture.ecommerce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class UserRegistrationTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        //browser configuration
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        driver.get("http://automationpractice.com/index.php");

    }


    @Test
    public void test() {

    }
}



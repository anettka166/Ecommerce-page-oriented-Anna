package com.academy.techcenture.ecommerce;

import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.config.Driver;
import com.academy.techcenture.ecommerce.pages.HomePage;
import com.academy.techcenture.ecommerce.pages.LoginPage;
import com.academy.techcenture.ecommerce.pages.UserAccountPage;
import com.academy.techcenture.ecommerce.pages.UserRegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class UserRegistrationTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("URL"));
    }

    @Test(priority = 0)
    public void userRegistrationTest() throws IOException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        UserRegisterPage registerPage = new UserRegisterPage(driver);
        UserAccountPage userAccountPage = new UserAccountPage(driver);

        homePage.clickSingInLink();
        loginPage.enterNewEmailAddress();
        registerPage.registerUser();
        userAccountPage.verifyAccountOptions();
        userAccountPage.navigateHome();
        homePage.signOut();


    }
}



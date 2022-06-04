package com.academy.techcenture.ecommerce;

import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.config.Driver;
import com.academy.techcenture.ecommerce.pages.HomePage;
import com.academy.techcenture.ecommerce.pages.LoginPage;
import com.academy.techcenture.ecommerce.pages.UserAccountPage;
import com.academy.techcenture.ecommerce.pages.UserRegistrationPage;
import com.academy.techcenture.ecommerce.pages.UserRegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

public class UserRegistrationTest {

    private WebDriver driver;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("URL"));
        softAssert = new SoftAssert();
    }

    @Test(priority = 0)
    public void userRegistrationPositiveTest() throws IOException {
        HomePage homePage = new HomePage(driver, softAssert);
        LoginPage loginPage = new LoginPage(driver, softAssert);
        UserRegistrationPage registerPage = new UserRegistrationPage(driver, softAssert);
        UserAccountPage userAccountPage = new UserAccountPage(driver, softAssert);

        homePage.clickSingInLink();
        loginPage.enterNewEmailAddress();
        registerPage.registerUser();
        userAccountPage.verifyAccountOptions();
        userAccountPage.navigateHome();
        homePage.signOut();
        softAssert.assertAll();
    }

    @Test
    public void userRegisterInvalidEmail() throws InterruptedException {

        HomePage homePage = new HomePage(driver, softAssert);
        LoginPage loginPage = new LoginPage(driver, softAssert);

        homePage.clickSingInLink();
        loginPage.verifyInvalidEmailAddresses();

        softAssert.assertAll();
    }

    @Test
    public void verifyErrorsOnRegisterPage() throws IOException {
        HomePage homePage = new HomePage(driver, softAssert);
        LoginPage loginPage = new LoginPage(driver, softAssert);
        UserRegistrationPage registerPage = new UserRegistrationPage(driver, softAssert);

        homePage.clickSingInLink();
        loginPage.enterNewEmailAddress();

        registerPage.verifyErrorsOnUserRegisterPage();

        System.out.println("end of test");
        softAssert.assertAll();
    }


    @AfterMethod
    public void cleanUp() {
        if (driver != null) {
            driver.quit();
        }


    }
}
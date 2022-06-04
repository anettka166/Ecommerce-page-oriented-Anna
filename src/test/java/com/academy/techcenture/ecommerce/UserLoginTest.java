package com.academy.techcenture.ecommerce;



import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.config.Driver;
import com.academy.techcenture.ecommerce.pages.HomePage;
import com.academy.techcenture.ecommerce.pages.LoginPage;
import com.academy.techcenture.ecommerce.pages.UserAccountPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserLoginTest {

    private WebDriver driver;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("URL"));
        softAssert = new SoftAssert();
    }

    @Test(priority = 0)
    public void userLoginPositive() {
        HomePage homePage = new HomePage(driver, softAssert);
        LoginPage loginPage = new LoginPage(driver, softAssert);
        UserAccountPage userAccountPage = new UserAccountPage(driver, softAssert);
        homePage.clickSingInLink();
        loginPage.login();
        userAccountPage.verifyAccountOptions();
        userAccountPage.navigateHome();
        homePage.signOut();

        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void userLoginNegative() throws InterruptedException {
        HomePage homePage = new HomePage(driver,softAssert);
        LoginPage loginPage = new LoginPage(driver,softAssert);
        homePage.clickSingInLink();
        loginPage.verifyLoginErrors();

        softAssert.assertAll();
    }

    @AfterMethod
    public void cleanUp(){
        if (driver != null){
            driver.quit();
        }
    }
}
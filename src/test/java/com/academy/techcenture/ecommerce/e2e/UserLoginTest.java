package com.academy.techcenture.ecommerce.e2e;



import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.config.Driver;
import com.academy.techcenture.ecommerce.pages.HomePage;
import com.academy.techcenture.ecommerce.pages.LoginPage;
import com.academy.techcenture.ecommerce.pages.UserAccountPage;
import com.academy.techcenture.ecommerce.utils.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class UserLoginTest {

    private WebDriver driver;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("URL"));
        softAssert = new SoftAssert();
    }

    @Test(priority = 0, dataProvider = "userLoginData")
    public void userLoginPositive(Map<String,String> users) {
        HomePage homePage = new HomePage(driver, softAssert);
        LoginPage loginPage = new LoginPage(driver, softAssert);
        UserAccountPage userAccountPage = new UserAccountPage(driver, softAssert);
        homePage.clickSingInLink();
        loginPage.login(users);
        userAccountPage.verifyAccountOptions();
        userAccountPage.navigateHome();
        homePage.signOut();

        softAssert.assertAll();
    }

    @Test(priority = 1, dataProvider = "invalidUserLoginData")
    public void userLoginNegative(Map<String,String> users) throws InterruptedException {
        HomePage homePage = new HomePage(driver,softAssert);
        LoginPage loginPage = new LoginPage(driver,softAssert);
        homePage.clickSingInLink();
        loginPage.verifyLoginErrors(users);

        softAssert.assertAll();
    }

    @AfterMethod
    public void cleanUp(){
        if (driver != null){
            driver.quit();
        }
    }


    @DataProvider(name = "userLoginData")
    public Object[][] getNewUsersData(){
        ExcelReader excelReader = new ExcelReader("src/main/resources/testData/ecommerce.xlsx", "userLogin");
        return excelReader.getData();
    }

    @DataProvider(name = "invalidUserLoginData")
    public Object[][] getInvalidUserLoginData(){
        ExcelReader excelReader = new ExcelReader("src/main/resources/testData/ecommerce.xlsx", "negativeLogin");
        return excelReader.getData();
    }
}
package com.academy.techcenture.ecommerce.e2e;

import com.academy.techcenture.ecommerce.base.BaseTest;
import com.academy.techcenture.ecommerce.pages.ContactUsPage;
import com.academy.techcenture.ecommerce.pages.HomePage;
import com.academy.techcenture.ecommerce.pages.LoginPage;
import com.academy.techcenture.ecommerce.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class ContactUsTest extends BaseTest {


    @Test(priority = 0, dataProvider = "contactUs")
    public void contactUsPositiveTest( Map<String,String> data ) throws IOException {

        HomePage homePage = new HomePage(driver,softAssert);
        LoginPage loginPage = new LoginPage(driver,softAssert);
        ContactUsPage contactUsPage = new ContactUsPage(driver, softAssert);

        if (data.get("guest").equals("no")){
            homePage.clickSingInLink();
            loginPage.login();
        }

        homePage.clickContactUsLink();

        contactUsPage.contactUs(data);

        softAssert.assertAll();
    }


    @DataProvider(name = "contactUs")
    public Object[][] getNewUsersData(){
        ExcelReader excelReader = new ExcelReader("src/main/resources/testData/ecommerce.xlsx", "contactUs");
        return excelReader.getData();
    }


}

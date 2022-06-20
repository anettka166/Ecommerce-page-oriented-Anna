package com.academy.techcenture.ecommerce.e2e;

import com.academy.techcenture.ecommerce.base.BaseTest;
import com.academy.techcenture.ecommerce.pages.HomePage;
import com.academy.techcenture.ecommerce.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductVerificationTest extends BaseTest {


    @Test(dataProvider = "dresses")
    public void verifyProductTabsTest(Map<String,String> data) throws InterruptedException {

        HomePage homePage = new HomePage(driver, softAssert);

        homePage.verifyProductTabs(data);

        softAssert.assertAll();
    }


    @DataProvider(name = "dresses")
    public Object[][] getDresses(){
        return new ExcelReader("src/main/resources/testData/ecommerce.xlsx","dresses").getData();
    }




}

package com.academy.techcenture.ecommerce.e2e;

import com.academy.techcenture.ecommerce.base.BaseTest;
import com.academy.techcenture.ecommerce.pages.SearchPage;
import com.academy.techcenture.ecommerce.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class SearchFunctionalityTest extends BaseTest {



    @Test(dataProvider = "searchKeyword")
    public void searchKeyWordTest(Map<String,String> data){

        SearchPage searchPage = new SearchPage(driver, softAssert);
        searchPage.verifySearch(data);
        softAssert.assertAll();
    }



    @DataProvider(name = "searchKeyword")
    public Object[][] getSearchKeyword(){
        return new ExcelReader("src/main/resources/testData/ecommerce.xlsx","searchKeywords").getData();

    }


}

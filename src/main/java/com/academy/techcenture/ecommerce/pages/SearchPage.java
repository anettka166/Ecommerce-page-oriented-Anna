package com.academy.techcenture.ecommerce.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class SearchPage extends HomePage{


    public SearchPage(WebDriver driver, SoftAssert softAssert){
        super(driver, softAssert);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='heading-counter']")
    private WebElement resultCount;

    @FindBy(xpath = "//span[@class='lighter']")
    private WebElement searchKeywordHeader;

    @FindBy(xpath = "//p[contains(@class,'alert-warning')]")
    private WebElement noResultAlertDiv;


    public void verifySearch(Map<String, String> data) {

        searchInputBox.sendKeys( data.get("searchKeyword") + Keys.ENTER);

        String count = resultCount.getText().trim().split(" ")[0];

        if (!count.equals("0")){
            softAssert.assertTrue(searchKeywordHeader.isDisplayed(), "Keyword header was not displayed");
            softAssert.assertEquals(searchKeywordHeader.getText().trim(),  "\"" + data.get("searchKeyword").toUpperCase() + "\"");
        }else{
            softAssert.assertTrue( noResultAlertDiv.isDisplayed(), "");
            softAssert.assertEquals( noResultAlertDiv.getText().trim(), data.get("expectedMessage") + " \""+ data.get("searchKeyword") + "\"" , "Mismatch between search keywords");
        }


    }

}

package com.academy.techcenture.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class UserAccountPage {
    //1. contructor of this page
    //2. find web elements
    //3. create methods for user actions
    //4. assertions

    private WebDriver driver;
    private SoftAssert softAssert;

    public UserAccountPage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);
    }

    //web elements

    @FindBy(className = "logout")
    private WebElement signOutLink;

    @FindBy(xpath = "//a[@class='account']")
    private WebElement accountLink;

    @FindBy(xpath = "//h1[@class='page-heading']")
    private WebElement myAccntHeader;

    @FindBy(xpath = "//div[contains(@class,'addresses-lists')]//ul/li//span")
    private List<WebElement> accountOptions;

    @FindBy(className = "info-account")
    private WebElement welcomeMsgTxt;

    @FindBy(xpath = "//a[@title='Home']")
    private WebElement homeBtn;


    private String[] accountOptionsExpected = {"order history and details",
            "my credit slips", "my addresses","my personal information","my wishlists"};

    //actions
    public void signOut(){
        softAssert.assertTrue(signOutLink.isDisplayed(), "Sign out is not displayed");
        signOutLink.click();
        System.out.println("Clicking sign out");
    }

    public void verifyAccountOptions(){
        softAssert.assertEquals(5, accountOptions.size());
        for (int i = 0; i < accountOptions.size(); i++) {
            softAssert.assertEquals(accountOptions.get(i).getText().toLowerCase(), accountOptionsExpected[i],
                    "account option did not match " + accountOptionsExpected[i]);
        }
    }

    public void navigateHome(){
        softAssert.assertTrue(homeBtn.isDisplayed());
        homeBtn.click();
    }




}



package com.academy.techcenture.ecommerce.pages;

import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

public class LoginPage {

    //1. web elements
    //2. methods (user actions)
    //3. assertions

    private WebDriver driver;
    private CommonUtils commonUtils;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.commonUtils = new CommonUtils();
        PageFactory.initElements(driver, this);
    }

    //web elements
    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "passwd")
    private WebElement passwdInput;

    @FindBy(id = "SubmitLogin")
    private WebElement loginBtn;

    @FindBy(xpath = "//h3[contains(text(),'Already registered?')]")
    private WebElement loginHeaderTxt;

    @FindBy(xpath = "//label[@for='email']")
    private WebElement emailLabel;

    @FindBy(xpath = "//label[@for='passwd']")
    private WebElement passwdLabel;

    @FindBy(xpath = "//a[contains(@title, 'Recover your forgotten')]")
    private WebElement forgotPswdLink;

    @FindBy(xpath = "//h3[contains(text(),'Create an account')]")
    private WebElement createAccountHeaderTxt;

    @FindBy(xpath = "//p[contains(text(),'Please enter your email address')]")
    private WebElement enterEmailTxt;

    @FindBy(xpath = "//label[@for='email_create']")
    private WebElement createAccountEmailLabel;

    @FindBy(id = "email_create")
    private WebElement createEmailInput;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountBtn;

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]//li")
    private WebElement authFailerErrorMsg;

    //actions
    public void login(){
        Assert.assertTrue(loginHeaderTxt.isDisplayed(), "Login Header was not displayed");
        Assert.assertTrue(emailLabel.isDisplayed(), "Email label was not dipslayed");
        Assert.assertTrue(passwdLabel.isDisplayed(), "Password label was not dipslayed");
        emailInput.clear();

        emailInput.sendKeys(ConfigReader.getProperty("username"));
        passwdInput.clear();
        passwdInput.sendKeys(ConfigReader.getProperty("password"));

        Assert.assertTrue(forgotPswdLink.isDisplayed(),"Forgot passwd is not displayed");
        Assert.assertTrue(loginBtn.isEnabled(), "Login Btn is not enabled");
        Assert.assertEquals("sign in", loginBtn.getText().toLowerCase().trim());
        loginBtn.click();
        System.out.println("Clicking login btn");
    }



    public void verifyLoginErrors() throws InterruptedException {
        //provide incorrect user creds
        emailInput.sendKeys("kevinlee1234@gmail.com");
        passwdInput.sendKeys("Kevin000");
        loginBtn.click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]//li")).getText().equals("Authentication failed."),"Error Message is not cocrect");

        emailInput.clear();
        passwdInput.clear();

        //provide invalid user credentials
        emailInput.sendKeys("kevinlee1234@gmail");
        passwdInput.sendKeys("Kevin123");
        loginBtn.click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]//li")).getText().equals("Invalid email address."),"Error Message is not cocrect");

        emailInput.clear();
        passwdInput.clear();

        loginBtn.click();
        //click sign in without provide email or passwd
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]//li")).getText().equals("An email address required."),"Error Message is not cocrect");


        //provide email but not password
        emailInput.sendKeys("kevinlee1234@gmail.com");
        loginBtn.click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]//li")).getText().equals("Password is required."),"Error Message is not cocrect");
    }


    public void enterNewEmailAddress() throws IOException {
        Assert.assertTrue(createAccountHeaderTxt.isDisplayed());
        Assert.assertTrue(enterEmailTxt.isDisplayed());
        Assert.assertTrue(createAccountEmailLabel.isDisplayed());

        String randomEmail = commonUtils.randomEmail();
        ConfigReader.setProperty("randomEmail", randomEmail);
        createEmailInput.sendKeys(randomEmail);

        Assert.assertTrue(createAccountBtn.isEnabled());
        createAccountBtn.click();
    }









}
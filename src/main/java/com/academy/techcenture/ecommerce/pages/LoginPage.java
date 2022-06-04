package com.academy.techcenture.ecommerce.pages;

import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

public class LoginPage {

    //1. web elements
    //2. methods (user actions)
    //3. assertions

    private WebDriver driver;
    private CommonUtils commonUtils;
    private WebDriverWait wait;
    private SoftAssert softAssert;

    public LoginPage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.commonUtils = new CommonUtils();
        this.softAssert = softAssert;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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

    @FindBy(id = "create_account_error")
    private WebElement invalidEmailErrorMsg;

    private String[] invalidEmailAddress = {"kevin.lee@gmail","kevin.leegmail.com","kevin.lee", "12345", "4875494@yahoo"};

    //actions
    public void login(){
        softAssert.assertTrue(loginHeaderTxt.isDisplayed(), "Login Header was NOT displayed");
        softAssert.assertTrue(emailLabel.isDisplayed(), "Email label was NOT dipslayed");
        softAssert.assertTrue(passwdLabel.isDisplayed(), "Password label was NOT dipslayed");
        emailInput.clear();

        emailInput.sendKeys(ConfigReader.getProperty("username"));
        passwdInput.clear();
        passwdInput.sendKeys(ConfigReader.getProperty("password"));

        softAssert.assertTrue(forgotPswdLink.isDisplayed(),"Forgot passwd is NOT displayed");
        softAssert.assertTrue(loginBtn.isEnabled(), "Login Btn is NOT enabled");
        softAssert.assertEquals("sign in", loginBtn.getText().toLowerCase().trim());
        loginBtn.click();
        System.out.println("Clicking login btn");
    }



    public void verifyLoginErrors() throws InterruptedException {
        //provide incorrect user creds
        emailInput.sendKeys("kevinlee1234@gmail.com");
        passwdInput.sendKeys("Kevin000");
        loginBtn.click();
        softAssert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]//li")).getText().equals("Authentication failed."),"Error Message is NOT cocrect");

        emailInput.clear();
        passwdInput.clear();

        //provide invalid user credentials
        emailInput.sendKeys("kevinlee1234@gmail");
        passwdInput.sendKeys("Kevin123");
        loginBtn.click();

        softAssert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]//li")).getText().equals("Invalid email address."),"Error Message is NOT cocrect");

        emailInput.clear();
        passwdInput.clear();

        loginBtn.click();
        //click sign in without provide email or passwd
        softAssert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]//li")).getText().equals("An email address required."),"Error Message is not cocrect");


        //provide email but not password
        emailInput.sendKeys("kevinlee1234@gmail.com");
        loginBtn.click();

        softAssert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]//li")).getText().equals("Password is required."),"Error Message is not cocrect");
    }


    public void enterNewEmailAddress() throws IOException {
        softAssert.assertTrue(createAccountHeaderTxt.isDisplayed(), "Create account header is NOT displayed");
        softAssert.assertTrue(enterEmailTxt.isDisplayed(), "Enter email text is NOT displayed");
        softAssert.assertTrue(createAccountEmailLabel.isDisplayed(), "Create account email label is NOT displayed");

        String randomEmail = commonUtils.randomEmail();
        ConfigReader.setProperty("randomEmail", randomEmail);
        createEmailInput.sendKeys(randomEmail);

        softAssert.assertTrue(createAccountBtn.isEnabled(), "Create account Btn is NOT displayed");
        createAccountBtn.click();
    }


    public void verifyInvalidEmailAddresses() throws InterruptedException {

        for (int i = 0; i < invalidEmailAddress.length; i++) {
            createEmailInput.sendKeys(invalidEmailAddress[i]); //kein@gmail
            softAssert.assertTrue(createAccountBtn.isEnabled(), "Create account Btn is NOT enabled");
            createAccountBtn.click();

            wait.until(ExpectedConditions.visibilityOf(invalidEmailErrorMsg));

            softAssert.assertEquals(invalidEmailErrorMsg.getText().trim(), "Invalid email address.", "Error message is not correct");
            Thread.sleep(1000);
            createEmailInput.clear();
        }
    }






}
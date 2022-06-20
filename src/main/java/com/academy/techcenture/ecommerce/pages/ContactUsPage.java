package com.academy.techcenture.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ContactUsPage {

    private WebDriver driver;
    private SoftAssert softAssert;
    private WebDriverWait wait;

    public ContactUsPage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert = softAssert;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "id_contact")
    private WebElement subjectHeadingDropDown;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "id_order")
    private WebElement orderRefInput; //for guests

    @FindBy(name = "id_order")
    private WebElement orderRefDropDown; //for valid users

    @FindBy(id = "message")
    private WebElement messageArea;

    @FindBy(id = "submitMessage")
    private WebElement sendBtn;

    @FindBy(xpath = "//p[contains(@class,'alert-success')]")
    private WebElement successAlertMessage;

    @FindBy(xpath = "//span[contains(.,'Home')]")
    private WebElement homeBtn;

    @FindBy(id = "fileUpload")
    private WebElement attachFileInput;


    public void contactUs(Map<String, String> data) {

        Select select = new Select(subjectHeadingDropDown);
        select.selectByVisibleText(data.get("subjectHeading"));

        if (data.get("guest").equals("no")){
            //choose from dropdown because we are valid customer
            Select orderRef = new Select(orderRefDropDown);
            List<WebElement> options = orderRef.getOptions();
            if (options.size() > 1){
                orderRef.selectByIndex(1);
            }
            softAssert.assertEquals(emailInput.getAttribute("value"), data.get("emailAddress"), "Email addresses do not match");

        }else{
            //sending order ref because we are a gust
            orderRefInput.sendKeys(data.get("orderReference"));
            emailInput.sendKeys(data.get("emailAddress"));
        }


        File f = new File("src/main/resources/pics/e2e-using-page-object-model_userLoginPositive.jpg");
        String absolute = f.getAbsolutePath();
        attachFileInput.sendKeys(absolute);

        messageArea.sendKeys(data.get("message"));
        softAssert.assertTrue(sendBtn.isEnabled(), "Send Button is not enabled");
        sendBtn.click();

        wait.until(ExpectedConditions.visibilityOf(successAlertMessage));

        softAssert.assertEquals(successAlertMessage.getText().trim(), data.get("successAlertMessage"), "Success alert message was not correct");

        goHome();
    }

    private void goHome(){
        softAssert.assertTrue(homeBtn.isDisplayed());
        homeBtn.click();
    }

}

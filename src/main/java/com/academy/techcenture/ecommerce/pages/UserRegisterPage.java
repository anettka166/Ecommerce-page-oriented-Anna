package com.academy.techcenture.ecommerce.pages;

import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserRegisterPage {

    private WebDriver driver;
    private CommonUtils commonUtils;
    private WebDriverWait wait;

    public UserRegisterPage(WebDriver driver){
        this.driver = driver;
        this.commonUtils = new CommonUtils();
        this.wait = new WebDriverWait( driver, Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("webDriverWait")) ));
        PageFactory.initElements(driver, this);
    }


    //create web elements for user registration page
    @FindBy(id = "customer_firstname")
    private WebElement firstNameInput;


    //actions and verifications
    public void registerUser(){
        fillOutPersonalDetailsSection();
        fillOutAddressSection();
    }


    private void fillOutPersonalDetailsSection(){
        //Todo: create web elements on a class level for registration form
        // complete the personal info and address sections
        String randomEmail = ConfigReader.getProperty("randomEmail");
        int atSign = randomEmail.indexOf("@");
        String[] fullName = randomEmail.substring(0, atSign).split("\\.");
        String firstName = fullName[1].substring(0,1).toUpperCase() + fullName[1].substring(1);
        String lastName = fullName[0].substring(0,1).toUpperCase() + fullName[0].substring(1);
        String password = commonUtils.randomPassword();

    }

    private void fillOutAddressSection(){
        //todo fill out address seciont inputs
    }

}
